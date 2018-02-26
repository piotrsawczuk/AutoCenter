package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;

import java.util.List;

public interface RepairService {
    void save(Repair repair);

    void deleteByIdAndCarId(Long id, Long carId);

    Repair findByIdAndCarId(Long id, Long carId);

    List<Repair> findAllByCarId(Long carId);

    List<RepairTotalCost> repairsTotalCostByCarId(Long carId);

    List<RepairTotalCost> repairsTotalCostByCarApiId(Long carApiId);
}