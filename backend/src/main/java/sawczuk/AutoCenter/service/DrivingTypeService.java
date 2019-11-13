package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.DrivingType;

import java.util.List;

public interface DrivingTypeService {
    DrivingType findByDrivingTypeIgnoreCase(String drivingTypeName) throws ResourceNotFoundException;

    DrivingType findByValue(Integer value) throws ResourceNotFoundException;

    List<DrivingType> findAll() throws ResourceNotFoundException;
}
