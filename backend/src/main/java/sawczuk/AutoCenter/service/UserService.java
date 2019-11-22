package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user) throws ResourceNotFoundException;

    void update(UserRequest userRequest, User user) throws PasswordException;

    void delete(Long id);

    Optional<User> findById(Long id);

    Optional<User> findByUsernameIgnoreCase(String username);

    Page<User> findAll(Pageable pageable);
}