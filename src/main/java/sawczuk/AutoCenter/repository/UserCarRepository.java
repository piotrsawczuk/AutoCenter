package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.UserCar;

import java.util.List;

public interface UserCarRepository extends CrudRepository<UserCar, Long> {
    List<UserCar> findAllByUserId(Long userId);
}
