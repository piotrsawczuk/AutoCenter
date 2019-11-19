package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.controller.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.model.dto.UserDetailRequest;
import sawczuk.AutoCenter.model.dto.UserDetailResponse;
import sawczuk.AutoCenter.service.UserDetailService;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.security.LoggedInUserProvider;

@Controller
@RequestMapping(value = "/user-details")
@RequiredArgsConstructor
public class UserDetailController {

    private final UserDetailService userDetailService;
    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserDetailResponse> getUserDetail() throws ResourceNotFoundException {
        Long userId = userService.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User ID", "username", LoggedInUserProvider.findLoggedInUsername()))
                .getId();
        return ResponseEntity.ok(DtoEntityMapper.map(userDetailService.findByUserId(userId), UserDetailResponse.class));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDetailResponse> saveUserDetail(@RequestBody UserDetailRequest userDetailRequest) throws ResourceNotFoundException {
        User user = userService.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", LoggedInUserProvider.findLoggedInUsername()));
        UserDetail userDetail = new UserDetail();
        DtoEntityMapper.mapWithNulls(userDetailRequest, userDetail);
        userDetail.setUser(user);
        userDetailService.save(userDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(userDetail, UserDetailResponse.class));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDetailResponse> editUserDetail(@RequestBody UserDetailRequest userDetailRequest) throws ResourceNotFoundException {
        UserDetail userDetail = userDetailService.findByUserUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername());
        if (userDetail == null) {
            throw new ResourceNotFoundException("User detail", "username", LoggedInUserProvider.findLoggedInUsername());
        }
        DtoEntityMapper.mapWithNulls(userDetailRequest, userDetail);
        userDetailService.save(userDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(userDetail, UserDetailResponse.class));
    }
}
