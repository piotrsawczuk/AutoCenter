package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.FuelEconomy;

import java.util.List;

public interface FuelEconomyRepository extends CrudRepository<FuelEconomy, Long> {
    List<FuelEconomy> findAllByCarIdOrderByDateDesc(Long carId);
}
