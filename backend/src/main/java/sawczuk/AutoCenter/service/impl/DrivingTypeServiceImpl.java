package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.DrivingType;
import sawczuk.AutoCenter.repository.DrivingTypeRepository;
import sawczuk.AutoCenter.service.DrivingTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrivingTypeServiceImpl implements DrivingTypeService {

    private final DrivingTypeRepository drivingTypeRepository;

    @Override
    public DrivingType findByDrivingTypeIgnoreCase(String drivingTypeName) throws ResourceNotFoundException {
        DrivingType drivingType = drivingTypeRepository.findByDrivingTypeIgnoreCase(drivingTypeName);
        if (drivingType == null)
            throw new ResourceNotFoundException("Driving type", "drivingTypeName", drivingTypeName);
        return drivingType;
    }

    @Override
    public DrivingType findByValue(Integer value) throws ResourceNotFoundException {
        DrivingType drivingType = drivingTypeRepository.findByValue(value);
        if (drivingType == null)
            throw new ResourceNotFoundException("Driving type", "value", value);
        return drivingType;
    }

    @Override
    public List<DrivingType> findAll() throws ResourceNotFoundException {
        List<DrivingType> drivingTypeList = drivingTypeRepository.findAll();
        if (drivingTypeList == null || drivingTypeList.isEmpty())
            throw new ResourceNotFoundException("Driving type list");
        return drivingTypeList;
    }
}
