package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    void save(User user);

    void update(UserDTO userDTO, User user) throws PasswordException;

    void delete(Long id);

    void delete(User user);

    User findOne(Long id);

    User findByUsername(String username);

    List<User> findAll();
}
