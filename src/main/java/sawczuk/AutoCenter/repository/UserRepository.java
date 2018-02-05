package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
