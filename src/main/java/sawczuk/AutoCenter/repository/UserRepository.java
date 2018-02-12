package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);

    List<User> findAll();
}
