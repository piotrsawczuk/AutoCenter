package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.repository.FuelEconomyRepository;
import sawczuk.AutoCenter.service.FuelEconomyService;
import sawczuk.AutoCenter.util.NumberUtils;

import java.util.List;

@Service
public class FuelEconomyServiceImpl implements FuelEconomyService {

    private FuelEconomyRepository fuelEconomyRepository;

    @Autowired
    public FuelEconomyServiceImpl(FuelEconomyRepository fuelEconomyRepository) {
        this.fuelEconomyRepository = fuelEconomyRepository;
    }

    @Override
    public void save(FuelEconomy fuelEconomy) {
        fuelEconomy.setConsumption(NumberUtils.calculateFuelEconomy(fuelEconomy.getDistanceDriven(), fuelEconomy.getFuelAmountFilled()));
        fuelEconomy.setFillUpCost(NumberUtils.calculateFillUpCost(fuelEconomy.getPricePerLitre(), fuelEconomy.getFuelAmountFilled()));
        fuelEconomyRepository.save(fuelEconomy);
    }

    @Override
    public void delete(Long id) {
        fuelEconomyRepository.delete(id);
    }

    @Override
    public FuelEconomy findOne(Long id) {
        return fuelEconomyRepository.findOne(id);
    }

    @Override
    public List<FuelEconomy> findAllByCarId(Long carId) {
        return fuelEconomyRepository.findAllByCarIdOrderByDateDesc(carId);
    }
}