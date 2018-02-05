package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
