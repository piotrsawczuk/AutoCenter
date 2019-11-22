package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.UserDetailRequest;
import sawczuk.AutoCenter.model.dto.UserDetailResponse;
import sawczuk.AutoCenter.service.UserDetailService;

@Controller
@RequestMapping(value = "/user-details")
@RequiredArgsConstructor
public class UserDetailController {

    private final UserDetailService userDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserDetailResponse> getUserDetail() throws ResourceNotFoundException {
        return ResponseEntity.ok(userDetailService.find());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDetailResponse> saveUserDetail(@RequestBody UserDetailRequest userDetailRequest)
            throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailService.save(userDetailRequest));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDetailResponse> editUserDetail(@RequestBody UserDetailRequest userDetailRequest)
            throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailService.update(userDetailRequest));
    }
}
