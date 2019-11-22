package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.RepairRequest;
import sawczuk.AutoCenter.model.dto.RepairResponse;
import sawczuk.AutoCenter.model.dto.RepairTotalCostResponse;

import java.util.List;

public interface RepairService {
    RepairResponse save(RepairRequest repairRequest, Long carId) throws ResourceNotFoundException;

    RepairResponse update(RepairRequest repairRequest, Long id, Long carId) throws ResourceNotFoundException;

    void deleteByIdAndCarId(Long id, Long carId);

    RepairResponse findByIdAndCarId(Long id, Long carId);

    Page<RepairResponse> findAllByCarId(Long carId, Pageable pageable);

    List<RepairTotalCostResponse> repairsTotalCostByCarId(Long carId);

    List<RepairTotalCostResponse> repairsTotalCostByCarApiId(Long carApiId);
}