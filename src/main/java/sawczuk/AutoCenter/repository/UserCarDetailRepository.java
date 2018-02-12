package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.UserCarDetail;

public interface UserCarDetailRepository extends CrudRepository<UserCarDetail, Long> {
    UserCarDetail findOneByUserCarId(Long carId);
}
