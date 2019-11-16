package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.FuelEconomyAverage;
import sawczuk.AutoCenter.repository.FuelEconomyRepository;
import sawczuk.AutoCenter.service.FuelEconomyService;
import sawczuk.AutoCenter.util.NumberUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelEconomyServiceImpl implements FuelEconomyService {

    private final FuelEconomyRepository fuelEconomyRepository;

    @Override
    public void save(FuelEconomy fuelEconomy) {
        fuelEconomy.setConsumption(NumberUtils.calculateFuelEconomy(fuelEconomy.getDistanceDriven(), fuelEconomy.getFuelAmountFilled()));
        fuelEconomy.setFillUpCost(NumberUtils.calculateFillUpCost(fuelEconomy.getPricePerLitre(), fuelEconomy.getFuelAmountFilled()));
        fuelEconomyRepository.save(fuelEconomy);
    }

    @Override
    public void deleteByIdAndCarId(Long id, Long carId) {
        fuelEconomyRepository.deleteByIdAndCarId(id, carId);
    }

    @Override
    public FuelEconomy findByIdAndCarId(Long id, Long carId) {
        return fuelEconomyRepository.findByIdAndCarId(id, carId);
    }

    @Override
    public List<FuelEconomy> findAllByCarId(Long carId) {
        return fuelEconomyRepository.findAllByCarIdOrderByDateDesc(carId);
    }

    @Override
    public Page<FuelEconomy> findAllByCarId(Long carId, Pageable pageable) {
        return fuelEconomyRepository.findAllByCarId(carId, pageable);
    }

    @Override
    public List<FuelEconomyAverage> fuelEconomyAveragesByCarId(Long carId) {
        return fuelEconomyRepository.fuelEconomyAveragesByCarId(carId);
    }

    @Override
    public List<FuelEconomyAverage> fuelEconomyAveragesByCarApiId(Long carApiId) {
        return fuelEconomyRepository.fuelEconomyAveragesByCarApiId(carApiId);
    }

}