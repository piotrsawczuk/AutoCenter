package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.FuelType;
import sawczuk.AutoCenter.repository.FuelTypeRepository;
import sawczuk.AutoCenter.service.FuelTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelType findByFuelTypeIgnoreCase(String fuelTypeName) throws ResourceNotFoundException {
        FuelType fuelType = fuelTypeRepository.findByFuelTypeIgnoreCase(fuelTypeName);
        if (fuelType == null)
            throw new ResourceNotFoundException("Fuel type", "fuelTypeName", fuelTypeName);
        return fuelType;
    }

    @Override
    public FuelType findByValue(Integer value) throws ResourceNotFoundException {
        FuelType fuelType = fuelTypeRepository.findByValue(value);
        if (fuelType == null)
            throw new ResourceNotFoundException("Fuel type", "value", value);
        return fuelType;
    }

    @Override
    public List<FuelType> findAll() throws ResourceNotFoundException {
        List<FuelType> fuelTypeList = fuelTypeRepository.findAll();
        if (fuelTypeList == null || fuelTypeList.isEmpty())
            throw new ResourceNotFoundException("Fuel type list");
        return fuelTypeList;
    }
}
