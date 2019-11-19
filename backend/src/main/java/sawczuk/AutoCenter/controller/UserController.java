package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.controller.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserRequest;
import sawczuk.AutoCenter.model.dto.UserResponse;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.security.LoggedInUserProvider;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createNewAccount(@RequestBody UserRequest userRequest) {
        User user = new User();
        DtoEntityMapper.map(userRequest, user);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(user, UserResponse.class));
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> updateAccount(@RequestBody UserRequest userRequest)
            throws PasswordException, ResourceNotFoundException{
        User user = userService.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", LoggedInUserProvider.findLoggedInUsername()));
        userService.update(userRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(user, UserResponse.class));
    }
}
