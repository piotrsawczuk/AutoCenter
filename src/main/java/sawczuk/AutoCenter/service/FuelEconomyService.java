package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.FuelEconomy;

import java.util.List;

public interface FuelEconomyService {
    void save(FuelEconomy fuelEconomy);

    void delete(Long id);

    FuelEconomy findOne(Long id);

    List<FuelEconomy> findAllByCarId(Long carId);
}