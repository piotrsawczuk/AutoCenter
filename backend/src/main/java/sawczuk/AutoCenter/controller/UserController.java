package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.UserRequest;
import sawczuk.AutoCenter.model.dto.UserResponse;
import sawczuk.AutoCenter.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createNewAccount(@RequestBody UserRequest userRequest) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequest));
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateAccount(@RequestBody UserRequest userRequest)
            throws PasswordException, ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(userRequest));
    }
}
