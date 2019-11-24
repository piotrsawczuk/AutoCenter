package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.DrivingTypeResponse;

import java.util.List;

public interface DrivingTypeService {
    DrivingTypeResponse findByDrivingTypeIgnoreCase(String drivingTypeName) throws ResourceNotFoundException;

    DrivingTypeResponse findByValue(Integer value) throws ResourceNotFoundException;

    List<DrivingTypeResponse> findAll() throws ResourceNotFoundException;
}
