package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.model.dto.UserDetailDTO;
import sawczuk.AutoCenter.service.UserDetailService;
import sawczuk.AutoCenter.service.UserService;

import java.util.List;

@Controller
public class UserDetailController {

    private UserDetailService userDetailService;
    private UserService userService;

//    @Autowired
//    public UserDetailController(UserDetailService userDetailService) {
//        this.userDetailService = userDetailService;
//    }

    @Autowired
    public UserDetailController(UserDetailService userDetailService, UserService userService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @RequestMapping(value = "/user-details", method = RequestMethod.POST)
    public ResponseEntity<UserDetail> saveUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstname(userDetailDTO.getFirstname());
        userDetail.setSurname(userDetailDTO.getSurname());
        userDetail.setAddress(userDetailDTO.getAddress());
        userDetail.setCity(userDetailDTO.getCity());
        userDetail.setZipCode(userDetailDTO.getZipCode());
        userDetail.setPhoneNumber(userDetailDTO.getPhoneNumber());
        //logged in user
        userDetail.setUser(userService.findByUsername("piotr"));
        userDetailService.save(userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user-details", method = RequestMethod.PUT)
    public ResponseEntity<UserDetail> editUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
        //logged in user
        UserDetail userDetail = userDetailService.findOneByUserUsername("piotr");
        if (!StringUtils.isEmpty(userDetailDTO.getFirstname()))
            userDetail.setFirstname(userDetailDTO.getFirstname());
        if (!StringUtils.isEmpty(userDetailDTO.getSurname()))
            userDetail.setSurname(userDetailDTO.getSurname());
        if (!StringUtils.isEmpty(userDetailDTO.getAddress()))
            userDetail.setAddress(userDetailDTO.getAddress());
        if (!StringUtils.isEmpty(userDetailDTO.getCity()))
            userDetail.setCity(userDetailDTO.getCity());
        if (!StringUtils.isEmpty(userDetailDTO.getZipCode()))
            userDetail.setZipCode(userDetailDTO.getZipCode());
        if (!StringUtils.isEmpty(userDetailDTO.getPhoneNumber()))
            userDetail.setPhoneNumber(userDetailDTO.getPhoneNumber());
        userDetailService.save(userDetail);
        return new ResponseEntity<>(userDetail, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user-details", method = RequestMethod.GET)
    public ResponseEntity<UserDetail> getUserDetail() {
        //
        Long userId = userService.findByUsername("piotr").getId();
        return new ResponseEntity<>(userDetailService.findOneByUserId(userId), HttpStatus.OK);
    }

}
//TODO change userService for UserUtil.findLoggedInUsername