package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.FuelType;
import sawczuk.AutoCenter.repository.FuelTypeRepository;
import sawczuk.AutoCenter.service.FuelTypeService;

import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    public FuelTypeServiceImpl(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @Override
    public FuelType findOneByFuelTypeIgnoreCase(String fuelType) {
        return fuelTypeRepository.findOneByFuelTypeIgnoreCase(fuelType);
    }

    @Override
    public FuelType findOneByValue(Integer value) {
        return fuelTypeRepository.findOneByValue(value);
    }

    @Override
    public List<FuelType> findAll() {
        return fuelTypeRepository.findAll();
    }
}
