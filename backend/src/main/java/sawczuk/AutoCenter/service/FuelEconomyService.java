package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.FuelEconomyAverageResponse;
import sawczuk.AutoCenter.model.dto.FuelEconomyRequest;
import sawczuk.AutoCenter.model.dto.FuelEconomyResponse;

import java.util.List;

public interface FuelEconomyService {
    FuelEconomyResponse findByIdAndCarId(Long id, Long carId);

    Page<FuelEconomyResponse> findAllByCarId(Long carId, Pageable pageable);

    FuelEconomyResponse save(FuelEconomyRequest fuelEconomy, Long carId) throws ResourceNotFoundException;

    FuelEconomyResponse update(FuelEconomyRequest fuelEconomy, Long id, Long carId) throws ResourceNotFoundException;

    void deleteByIdAndCarId(Long id, Long carId);

    List<FuelEconomyAverageResponse> fuelEconomyAveragesByCarId(Long carId);

    List<FuelEconomyAverageResponse> fuelEconomyAveragesByCarApiId(Long carApiId);
}