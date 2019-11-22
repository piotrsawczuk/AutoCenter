package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.FuelTypeResponse;

import java.util.List;

public interface FuelTypeService {
    FuelTypeResponse findByFuelTypeIgnoreCase(String fuelTypeName) throws ResourceNotFoundException;

    FuelTypeResponse findByValue(Integer value) throws ResourceNotFoundException;

    List<FuelTypeResponse> findAll() throws ResourceNotFoundException;
}
