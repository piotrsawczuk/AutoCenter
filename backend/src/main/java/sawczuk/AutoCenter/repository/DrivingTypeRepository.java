package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.DrivingType;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DrivingTypeRepository extends CrudRepository<DrivingType, Long> {
    Optional<DrivingType> findByDrivingTypeIgnoreCase(String drivingType);

    Optional<DrivingType> findByValue(Integer value);

    List<DrivingType> findAll();
}
