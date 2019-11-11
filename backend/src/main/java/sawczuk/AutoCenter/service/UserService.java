package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    void update(UserDTO userDTO, User user) throws PasswordException;

    void delete(Long id);

    void delete(User user);

    Optional<User> findById(Long id);

    User findByUsernameIgnoreCase(String username);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);
}