package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.model.dto.UserDetailDTO;
import sawczuk.AutoCenter.service.UserDetailService;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.security.LoggedInUserProvider;

@Controller
@RequiredArgsConstructor
public class UserDetailController {

    private final UserDetailService userDetailService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-details", method = RequestMethod.POST)
    public ResponseEntity<UserDetail> saveUserDetail(@RequestBody UserDetailDTO userDetailDTO) throws ResourceNotFoundException {
        User user = userService.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", LoggedInUserProvider.findLoggedInUsername()));
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstname(userDetailDTO.getFirstname());
        userDetail.setSurname(userDetailDTO.getSurname());
        userDetail.setAddress(userDetailDTO.getAddress());
        userDetail.setCity(userDetailDTO.getCity());
        userDetail.setZipCode(userDetailDTO.getZipCode());
        userDetail.setPhoneNumber(userDetailDTO.getPhoneNumber());
        userDetail.setUser(user);
        userDetailService.save(userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-details", method = RequestMethod.PUT)
    public ResponseEntity<UserDetail> editUserDetail(@RequestBody UserDetailDTO userDetailDTO) throws ResourceNotFoundException {
        UserDetail userDetail = userDetailService.findByUserUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername());
        if (userDetail == null) {
            throw new ResourceNotFoundException("User detail", "username", LoggedInUserProvider.findLoggedInUsername());
        }
        userDetail.setFirstname(userDetailDTO.getFirstname());
        userDetail.setSurname(userDetailDTO.getSurname());
        userDetail.setAddress(userDetailDTO.getAddress());
        userDetail.setCity(userDetailDTO.getCity());
        userDetail.setZipCode(userDetailDTO.getZipCode());
        userDetail.setPhoneNumber(userDetailDTO.getPhoneNumber());
        userDetailService.save(userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user-details", method = RequestMethod.GET)
    public ResponseEntity<UserDetail> getUserDetail() throws ResourceNotFoundException {
        Long userId = userService.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User ID", "username", LoggedInUserProvider.findLoggedInUsername()))
                .getId();
        return new ResponseEntity<>(userDetailService.findByUserId(userId), HttpStatus.OK);
    }
}