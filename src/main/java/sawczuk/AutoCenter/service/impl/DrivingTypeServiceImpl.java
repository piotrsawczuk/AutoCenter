package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.DrivingType;
import sawczuk.AutoCenter.repository.DrivingTypeRepository;
import sawczuk.AutoCenter.service.DrivingTypeService;

import java.util.List;

@Service
public class DrivingTypeServiceImpl implements DrivingTypeService {

    private DrivingTypeRepository drivingTypeRepository;

    @Autowired
    public DrivingTypeServiceImpl(DrivingTypeRepository drivingTypeRepository) {
        this.drivingTypeRepository = drivingTypeRepository;
    }

    @Override
    public DrivingType findOneByDrivingTypeIgnoreCase(String drivingType) {
        return drivingTypeRepository.findOneByDrivingTypeIgnoreCase(drivingType);
    }

    @Override
    public DrivingType findOneByValue(Integer value) {
        return drivingTypeRepository.findOneByValue(value);
    }

    @Override
    public List<DrivingType> findAll() {
        return drivingTypeRepository.findAll();
    }
}
