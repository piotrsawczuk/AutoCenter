package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.UserDetail;

import java.util.Optional;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
    Optional<UserDetail> findByUserId(Long userId);

    Optional<UserDetail> findByUserUsernameIgnoreCase(String username);
}
