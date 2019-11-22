package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.FuelType;

import java.util.List;
import java.util.Optional;

public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
    Optional<FuelType> findByFuelTypeIgnoreCase(String fuelType);

    Optional<FuelType> findByValue(Integer value);

    List<FuelType> findAll();
}

