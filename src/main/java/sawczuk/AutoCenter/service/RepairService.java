package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;

import java.util.List;

public interface RepairService {
    void save(Repair repair);

    void delete(Long id);

    Repair findOne(Long id);

    List<Repair> findAllByCarId(Long carId);

    List<RepairTotalCost> repairsTotalCostByCarId(Long carId);

    List<RepairTotalCost> repairsTotalCostByCarApiId(Long carApiId);
}