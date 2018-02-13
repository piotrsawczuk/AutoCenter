package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.DrivingType;

import java.util.List;

public interface DrivingTypeService {
    DrivingType findOneByDrivingTypeIgnoreCase(String drivingType);

    DrivingType findOneByValue(Integer value);

    List<DrivingType> findAll();
}
