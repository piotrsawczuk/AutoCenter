package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.DrivingType;
import sawczuk.AutoCenter.model.dto.DrivingTypeResponse;
import sawczuk.AutoCenter.repository.DrivingTypeRepository;
import sawczuk.AutoCenter.service.DrivingTypeService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrivingTypeServiceImpl implements DrivingTypeService {

    private final DrivingTypeRepository drivingTypeRepository;

    @Override
    public DrivingTypeResponse findByDrivingTypeIgnoreCase(String drivingTypeName) throws ResourceNotFoundException {
        DrivingType drivingType = drivingTypeRepository.findByDrivingTypeIgnoreCase(drivingTypeName);
        if (drivingType == null) {
            throw new ResourceNotFoundException("Driving type", "drivingTypeName", drivingTypeName);
        }
        return DtoEntityMapper.map(drivingType, DrivingTypeResponse.class);
    }

    @Override
    public DrivingTypeResponse findByValue(Integer value) throws ResourceNotFoundException {
        DrivingType drivingType = drivingTypeRepository.findByValue(value);
        if (drivingType == null) {
            throw new ResourceNotFoundException("Driving type", "value", value);
        }
        return DtoEntityMapper.map(drivingType, DrivingTypeResponse.class);
    }

    @Override
    public List<DrivingTypeResponse> findAll() throws ResourceNotFoundException {
        List<DrivingType> drivingTypeList = drivingTypeRepository.findAll();
        if (drivingTypeList == null || drivingTypeList.isEmpty()) {
            throw new ResourceNotFoundException("Driving type list");
        }
        return DtoEntityMapper.mapAll(drivingTypeList, DrivingTypeResponse.class);
    }
}
