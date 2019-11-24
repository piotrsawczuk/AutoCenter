package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.FuelConsumption;
import sawczuk.AutoCenter.model.dto.FuelConsumptionAverageResponse;
import sawczuk.AutoCenter.model.dto.FuelConsumptionRequest;
import sawczuk.AutoCenter.model.dto.FuelConsumptionResponse;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.repository.DrivingTypeRepository;
import sawczuk.AutoCenter.repository.FuelConsumptionRepository;
import sawczuk.AutoCenter.repository.FuelTypeRepository;
import sawczuk.AutoCenter.service.FuelConsumptionService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.util.NumberUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelConsumptionServiceImpl implements FuelConsumptionService {

    private final FuelConsumptionRepository fuelConsumptionRepository;
    private final CarRepository carRepository;
    private final DrivingTypeRepository drivingTypeRepository;
    private final FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelConsumptionResponse save(FuelConsumptionRequest fuelConsumptionRequest, Long carId) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        FuelConsumption fuelConsumption = new FuelConsumption();
        DtoEntityMapper.map(fuelConsumptionRequest, fuelConsumption);
        fuelConsumption.setDrivingType(drivingTypeRepository.findByValue(fuelConsumptionRequest.getDrivingType())
                .orElseThrow(() -> new ResourceNotFoundException("DrivingType", "value", fuelConsumptionRequest.getDrivingType())));
        fuelConsumption.setFuelType(fuelTypeRepository.findByValue(fuelConsumptionRequest.getFuelType())
                .orElseThrow(() -> new ResourceNotFoundException("FuelType", "value", fuelConsumptionRequest.getFuelType())));
        fuelConsumption.setCar(car);
        fuelConsumption.setConsumption(NumberUtils.calculateFuelConsumption(fuelConsumption.getDistanceDriven(), fuelConsumption.getFuelAmountFilled()));
        fuelConsumption.setFillUpCost(NumberUtils.calculateFillUpCost(fuelConsumption.getPricePerLitre(), fuelConsumption.getFuelAmountFilled()));
        fuelConsumptionRepository.save(fuelConsumption);
        return DtoEntityMapper.map(fuelConsumption, FuelConsumptionResponse.class);
    }

    @Override
    public FuelConsumptionResponse update(FuelConsumptionRequest fuelConsumptionRequest, Long id, Long carId) throws ResourceNotFoundException {
        FuelConsumption fuelConsumption = fuelConsumptionRepository.findByIdAndCarId(id, carId)
                .orElseThrow(() -> new ResourceNotFoundException("Fuel consumption", "id", id, "carId", carId));
        DtoEntityMapper.map(fuelConsumptionRequest, fuelConsumption);
        if (fuelConsumptionRequest.getDrivingType() != null) {
            fuelConsumption.setDrivingType(drivingTypeRepository.findByValue(fuelConsumptionRequest.getDrivingType())
                    .orElseThrow(() -> new ResourceNotFoundException("DrivingType", "value", fuelConsumptionRequest.getDrivingType())));
        }
        if (fuelConsumptionRequest.getFuelType() != null) {
            fuelConsumption.setFuelType(fuelTypeRepository.findByValue(fuelConsumptionRequest.getFuelType())
                    .orElseThrow(() -> new ResourceNotFoundException("FuelType", "value", fuelConsumptionRequest.getFuelType())));
        }

        fuelConsumptionRepository.save(fuelConsumption);
        return DtoEntityMapper.map(fuelConsumption, FuelConsumptionResponse.class);
    }

    @Override
    public void deleteByIdAndCarId(Long id, Long carId) {
        fuelConsumptionRepository.deleteByIdAndCarId(id, carId);
    }

    @Override
    public FuelConsumptionResponse findByIdAndCarId(Long id, Long carId) {
        return DtoEntityMapper.map(fuelConsumptionRepository.findByIdAndCarId(id, carId), FuelConsumptionResponse.class);
    }

    @Override
    public Page<FuelConsumptionResponse> findAllByCarId(Long carId, Pageable pageable) {
        return DtoEntityMapper.mapAll(fuelConsumptionRepository.findAllByCarId(carId, pageable), FuelConsumptionResponse.class);
    }

    @Override
    public List<FuelConsumptionAverageResponse> fuelConsumptionAveragesByCarId(Long carId) {
        return DtoEntityMapper.mapAll(
                fuelConsumptionRepository.fuelConsumptionAveragesByCarId(carId),
                FuelConsumptionAverageResponse.class);
    }

    @Override
    public List<FuelConsumptionAverageResponse> fuelConsumptionAveragesByCarApiId(Long carApiId) {
        return DtoEntityMapper.mapAll(
                fuelConsumptionRepository.fuelConsumptionAveragesByCarApiId(carApiId),
                FuelConsumptionAverageResponse.class);
    }

}