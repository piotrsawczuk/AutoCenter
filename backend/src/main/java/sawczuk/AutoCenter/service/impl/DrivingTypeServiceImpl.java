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
        return DtoEntityMapper.map(
                drivingTypeRepository.findByDrivingTypeIgnoreCase(drivingTypeName)
                        .orElseThrow(() -> new ResourceNotFoundException("DrivingType", "drivingTypeName", drivingTypeName)),
                DrivingTypeResponse.class);
    }

    @Override
    public DrivingTypeResponse findByValue(Integer value) throws ResourceNotFoundException {
        return DtoEntityMapper.map(
                drivingTypeRepository.findByValue(value)
                        .orElseThrow(() -> new ResourceNotFoundException("Driving type", "value", value)),
                DrivingTypeResponse.class);
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
