package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.FuelType;
import sawczuk.AutoCenter.repository.FuelTypeRepository;

import java.util.List;

public interface FuelTypeService {
    FuelType findOneByFuelTypeIgnoreCase(String fuelType);

    FuelType findOneByValue(Integer value);

    List<FuelType> findAll();
}
