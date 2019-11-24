package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.FuelConsumptionAverageResponse;
import sawczuk.AutoCenter.model.dto.FuelConsumptionRequest;
import sawczuk.AutoCenter.model.dto.FuelConsumptionResponse;

import java.util.List;

public interface FuelConsumptionService {
    FuelConsumptionResponse findByIdAndCarId(Long id, Long carId);

    Page<FuelConsumptionResponse> findAllByCarId(Long carId, Pageable pageable);

    FuelConsumptionResponse save(FuelConsumptionRequest fuelConsumption, Long carId) throws ResourceNotFoundException;

    FuelConsumptionResponse update(FuelConsumptionRequest fuelConsumption, Long id, Long carId) throws ResourceNotFoundException;

    void deleteByIdAndCarId(Long id, Long carId);

    List<FuelConsumptionAverageResponse> fuelConsumptionAveragesByCarId(Long carId);

    List<FuelConsumptionAverageResponse> fuelConsumptionAveragesByCarApiId(Long carApiId);
}