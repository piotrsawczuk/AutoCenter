package sawczuk.AutoCenter.repository;

import org.springframework.data.repository.CrudRepository;
import sawczuk.AutoCenter.model.Repair;

import java.util.List;

public interface RepairRepository extends CrudRepository<Repair, Long> {
    List<Repair> findAllByCarIdOrderByDateDesc(Long carId);
}