package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.FuelType;
import sawczuk.AutoCenter.model.dto.FuelTypeResponse;
import sawczuk.AutoCenter.repository.FuelTypeRepository;
import sawczuk.AutoCenter.service.FuelTypeService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelTypeResponse findByFuelTypeIgnoreCase(String fuelTypeName) throws ResourceNotFoundException {
        FuelType fuelType = fuelTypeRepository.findByFuelTypeIgnoreCase(fuelTypeName);
        if (fuelType == null) {
            throw new ResourceNotFoundException("Fuel type", "fuelTypeName", fuelTypeName);
        }
        return DtoEntityMapper.map(fuelType, FuelTypeResponse.class);
    }

    @Override
    public FuelTypeResponse findByValue(Integer value) throws ResourceNotFoundException {
        FuelType fuelType = fuelTypeRepository.findByValue(value);
        if (fuelType == null) {
            throw new ResourceNotFoundException("Fuel type", "value", value);
        }
        return DtoEntityMapper.map(fuelType, FuelTypeResponse.class);
    }

    @Override
    public List<FuelTypeResponse> findAll() throws ResourceNotFoundException {
        List<FuelType> fuelTypeList = fuelTypeRepository.findAll();
        if (fuelTypeList == null || fuelTypeList.isEmpty()) {
            throw new ResourceNotFoundException("Fuel type list");
        }
        return DtoEntityMapper.mapAll(fuelTypeList, FuelTypeResponse.class);
    }
}
