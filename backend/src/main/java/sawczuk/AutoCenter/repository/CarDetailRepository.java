package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.CarDetail;

import java.util.Optional;

public interface CarDetailRepository extends CrudRepository<CarDetail, Long> {
    Optional<CarDetail> findByCarId(Long carId);
}
