package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.RepairTotalCost;
import sawczuk.AutoCenter.model.dto.RepairRequest;
import sawczuk.AutoCenter.model.dto.RepairResponse;
import sawczuk.AutoCenter.model.dto.RepairTotalCostResponse;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.repository.ExploitationTypeRepository;
import sawczuk.AutoCenter.repository.RepairRepository;
import sawczuk.AutoCenter.service.RepairService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repairRepository;
    private final CarRepository carRepository;
    private final ExploitationTypeRepository exploitationTypeRepository;

    @Override
    public RepairResponse save(RepairRequest repairRequest, Long carId) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        Repair repair = new Repair();
        DtoEntityMapper.map(repairRequest, repair);
        repair.setExploitationType(exploitationTypeRepository.findByValue(repairRequest.getExploitationType())
                .orElseThrow(() -> new ResourceNotFoundException("ExploitationType", "value", repairRequest.getExploitationType())));
        repair.setCar(car);
        repairRepository.save(repair);
        return DtoEntityMapper.map(repair, RepairResponse.class);
    }

    @Override
    public RepairResponse update(RepairRequest repairRequest, Long id, Long carId) throws ResourceNotFoundException {
        Repair repair = repairRepository.findByIdAndCarId(id, carId);
        if (repair == null) {
            throw new ResourceNotFoundException("Repair", "id", id, "carId", carId);
        }

        DtoEntityMapper.map(repairRequest, repair);
        if (repairRequest.getExploitationType() != null) {
            repair.setExploitationType(exploitationTypeRepository.findByValue(repairRequest.getExploitationType())
                    .orElseThrow(() -> new ResourceNotFoundException("ExploitationType", "value", repairRequest.getExploitationType())));
        }
        repairRepository.save(repair);
        return DtoEntityMapper.map(repair, RepairResponse.class);
    }

    @Override
    public void deleteByIdAndCarId(Long id, Long carId) {
        repairRepository.deleteByIdAndCarId(id, carId);
    }

    @Override
    public RepairResponse findByIdAndCarId(Long id, Long carId) {
        return DtoEntityMapper.map(repairRepository.findByIdAndCarId(id, carId), RepairResponse.class);
    }

    @Override
    public Page<RepairResponse> findAllByCarId(Long carId, Pageable pageable) {
        return DtoEntityMapper.mapAll(repairRepository.findAllByCarId(carId, pageable), RepairResponse.class);
    }

    @Override
    public List<RepairTotalCostResponse> repairsTotalCostByCarId(Long carId) {
        return DtoEntityMapper.mapAll(repairRepository.repairsTotalCostByCarId(carId), RepairTotalCostResponse.class);
    }

    @Override
    public List<RepairTotalCostResponse> repairsTotalCostByCarApiId(Long carApiId) {
        Map<Integer, Double> avgsGroupedByExploitationType = repairRepository.repairsTotalCostByCarApiId(carApiId).stream()
                .collect(Collectors.groupingBy(RepairTotalCost::getExploitationTypeValue, Collectors.averagingDouble(RepairTotalCost::getTotalCost)));
        List<RepairTotalCost> avgTotalCosts = avgsGroupedByExploitationType.entrySet().stream()
                .map(entry -> new RepairTotalCost(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return DtoEntityMapper.mapAll(avgTotalCosts, RepairTotalCostResponse.class);
    }
}