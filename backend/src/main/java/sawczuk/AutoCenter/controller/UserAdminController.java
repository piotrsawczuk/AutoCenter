package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.controller.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserRequest;
import sawczuk.AutoCenter.model.dto.UserResponse;
import sawczuk.AutoCenter.service.UserService;

@Controller
@RequestMapping(value = "/users")
@PreAuthorize("hasAuthority('admin')")
@RequiredArgsConstructor
public class UserAdminController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserResponse>> findAllAccounts(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault(sort = "username", direction = Sort.Direction.ASC)
                    Pageable pageable) {
        return ResponseEntity.ok(DtoEntityMapper.mapAll(userService.findAll(pageable), UserResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findAccountById(@PathVariable Long id)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        User user = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return ResponseEntity.ok(DtoEntityMapper.map(user, UserResponse.class));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createNewAdminAccount(@RequestBody UserRequest userRequest)
            throws ResourceNotFoundException {
        User user = new User();
        DtoEntityMapper.map(userRequest, user);
        user.setRoleAdmin(true);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(user, UserResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
