package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.dto.FuelEconomyAverageResponse;
import sawczuk.AutoCenter.model.dto.FuelEconomyRequest;
import sawczuk.AutoCenter.model.dto.FuelEconomyResponse;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.repository.DrivingTypeRepository;
import sawczuk.AutoCenter.repository.FuelEconomyRepository;
import sawczuk.AutoCenter.repository.FuelTypeRepository;
import sawczuk.AutoCenter.service.FuelEconomyService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.util.NumberUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelEconomyServiceImpl implements FuelEconomyService {

    private final FuelEconomyRepository fuelEconomyRepository;
    private final CarRepository carRepository;
    private final DrivingTypeRepository drivingTypeRepository;
    private final FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelEconomyResponse save(FuelEconomyRequest fuelEconomyRequest, Long carId) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        FuelEconomy fuelEconomy = new FuelEconomy();
        DtoEntityMapper.map(fuelEconomyRequest, fuelEconomy);
        fuelEconomy.setDrivingType(drivingTypeRepository.findByValue(fuelEconomyRequest.getDrivingType())
                .orElseThrow(() -> new ResourceNotFoundException("DrivingType", "value", fuelEconomyRequest.getDrivingType())));
        fuelEconomy.setFuelType(fuelTypeRepository.findByValue(fuelEconomyRequest.getFuelType())
                .orElseThrow(() -> new ResourceNotFoundException("FuelType", "value", fuelEconomyRequest.getFuelType())));
        fuelEconomy.setCar(car);
        fuelEconomy.setConsumption(NumberUtils.calculateFuelEconomy(fuelEconomy.getDistanceDriven(), fuelEconomy.getFuelAmountFilled()));
        fuelEconomy.setFillUpCost(NumberUtils.calculateFillUpCost(fuelEconomy.getPricePerLitre(), fuelEconomy.getFuelAmountFilled()));
        fuelEconomyRepository.save(fuelEconomy);
        return DtoEntityMapper.map(fuelEconomy, FuelEconomyResponse.class);
    }

    @Override
    public FuelEconomyResponse update(FuelEconomyRequest fuelEconomyRequest, Long id, Long carId) throws ResourceNotFoundException {
        FuelEconomy fuelEconomy = fuelEconomyRepository.findByIdAndCarId(id, carId)
                .orElseThrow(() -> new ResourceNotFoundException("Fuel economy", "id", id, "carId", carId));
        DtoEntityMapper.map(fuelEconomyRequest, fuelEconomy);
        if (fuelEconomyRequest.getDrivingType() != null) {
            fuelEconomy.setDrivingType(drivingTypeRepository.findByValue(fuelEconomyRequest.getDrivingType())
                    .orElseThrow(() -> new ResourceNotFoundException("DrivingType", "value", fuelEconomyRequest.getDrivingType())));
        }
        if (fuelEconomyRequest.getFuelType() != null) {
            fuelEconomy.setFuelType(fuelTypeRepository.findByValue(fuelEconomyRequest.getFuelType())
                    .orElseThrow(() -> new ResourceNotFoundException("FuelType", "value", fuelEconomyRequest.getFuelType())));
        }

        fuelEconomyRepository.save(fuelEconomy);
        return DtoEntityMapper.map(fuelEconomy, FuelEconomyResponse.class);
    }

    @Override
    public void deleteByIdAndCarId(Long id, Long carId) {
        fuelEconomyRepository.deleteByIdAndCarId(id, carId);
    }

    @Override
    public FuelEconomyResponse findByIdAndCarId(Long id, Long carId) {
        return DtoEntityMapper.map(fuelEconomyRepository.findByIdAndCarId(id, carId), FuelEconomyResponse.class);
    }

    @Override
    public Page<FuelEconomyResponse> findAllByCarId(Long carId, Pageable pageable) {
        return DtoEntityMapper.mapAll(fuelEconomyRepository.findAllByCarId(carId, pageable), FuelEconomyResponse.class);
    }

    @Override
    public List<FuelEconomyAverageResponse> fuelEconomyAveragesByCarId(Long carId) {
        return DtoEntityMapper.mapAll(
                fuelEconomyRepository.fuelEconomyAveragesByCarId(carId),
                FuelEconomyAverageResponse.class);
    }

    @Override
    public List<FuelEconomyAverageResponse> fuelEconomyAveragesByCarApiId(Long carApiId) {
        return DtoEntityMapper.mapAll(
                fuelEconomyRepository.fuelEconomyAveragesByCarApiId(carApiId),
                FuelEconomyAverageResponse.class);
    }

}