package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.UserRequest;
import sawczuk.AutoCenter.model.dto.UserResponse;

public interface UserService {
    UserResponse save(UserRequest userRequest) throws ResourceNotFoundException;

    UserResponse save(UserRequest userRequest, boolean isRoleAdmin) throws ResourceNotFoundException;

    UserResponse update(UserRequest userRequest) throws PasswordException, ResourceNotFoundException;

    void delete(Long id);

    UserResponse findById(Long id) throws ResourceNotFoundException;

    Page<UserResponse> findAll(Pageable pageable);
}