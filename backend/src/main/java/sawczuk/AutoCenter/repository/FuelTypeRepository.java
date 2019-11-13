package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.FuelType;

import java.util.List;

public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
    FuelType findByFuelTypeIgnoreCase(String fuelType);

    FuelType findByValue(Integer value);

    List<FuelType> findAll();
}

