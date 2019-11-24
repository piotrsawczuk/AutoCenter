package sawczuk.AutoCenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sawczuk.AutoCenter.model.FuelConsumption;
import sawczuk.AutoCenter.model.FuelConsumptionAverage;

import java.util.List;
import java.util.Optional;

public interface FuelConsumptionRepository extends PagingAndSortingRepository<FuelConsumption, Long> {
    Optional<FuelConsumption> findByIdAndCarId(Long id, Long carId);

    Page<FuelConsumption> findAllByCarId(Long carId, Pageable pageable);

    @Transactional
    void deleteByIdAndCarId(Long id, Long carId);

    @Query("SELECT new sawczuk.AutoCenter.model.FuelConsumptionAverage(d.value, f.value, AVG(e.consumption)) " +
            "FROM FuelConsumption e " +
            "JOIN e.car c " +
            "JOIN e.drivingType d " +
            "JOIN e.fuelType f " +
            "WHERE c.id = :carId " +
            "GROUP BY d.value, f.value")
    List<FuelConsumptionAverage> fuelConsumptionAveragesByCarId(@Param("carId") Long carId);

    @Query("SELECT new sawczuk.AutoCenter.model.FuelConsumptionAverage(d.value, f.value, AVG(e.consumption)) " +
            "FROM FuelConsumption e " +
            "JOIN e.car c " +
            "JOIN e.drivingType d " +
            "JOIN e.fuelType f " +
            "WHERE c.carApiId = :carApiId " +
            "GROUP BY d.value, f.value")
    List<FuelConsumptionAverage> fuelConsumptionAveragesByCarApiId(@Param("carApiId") Long carApiId);
}
