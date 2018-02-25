package sawczuk.AutoCenter.service;

import org.springframework.data.repository.query.Param;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.FuelEconomyAverage;

import java.util.List;

public interface FuelEconomyService {
    void save(FuelEconomy fuelEconomy);

    void delete(Long id);

    FuelEconomy findOne(Long id);

    List<FuelEconomy> findAllByCarId(Long carId);

    List<FuelEconomyAverage> fuelEconomyAveragesByCarApiId(@Param("carApiId") Long carApiId);
}