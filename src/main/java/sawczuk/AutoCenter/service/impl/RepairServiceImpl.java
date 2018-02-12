package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.repository.RepairRepository;
import sawczuk.AutoCenter.service.RepairService;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    private RepairRepository repairRepository;

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public void save(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public void delete(Long id) {
        repairRepository.delete(id);
    }

    @Override
    public Repair findOne(Long id) {
        return repairRepository.findOne(id);
    }

    @Override
    public List<Repair> findAllByUserCarId(Long carId) {
        return repairRepository.findAllByUserCarId(carId);
    }
}