package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.dto.RepairDTO;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.ExploitationTypeService;
import sawczuk.AutoCenter.service.RepairService;

import java.util.List;

@Controller
public class RepairController {

    private RepairService repairService;
    private CarService carService;
    private ExploitationTypeService exploitationTypeService;

    @Autowired
    public RepairController(RepairService repairService, CarService carService, ExploitationTypeService exploitationTypeService) {
        this.repairService = repairService;
        this.carService = carService;
        this.exploitationTypeService = exploitationTypeService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs", method = RequestMethod.POST)
    public ResponseEntity<Repair> saveRepair(@PathVariable(value = "carId") Long carId, @RequestBody RepairDTO repairDTO) {
        Repair repair = new Repair();
        if (repairDTO.getDate() != null)
            repair.setDate(repairDTO.getDate());
        repair.setCar(carService.findOne(carId));
        repair.setMileage(repairDTO.getMileage());
        repair.setDescription(repairDTO.getDescription());
        repair.setExploitationType(exploitationTypeService.findOneByValue(repairDTO.getExploitationType()));
        repair.setCost(repairDTO.getCost());
        repairService.save(repair);
        return new ResponseEntity<>(repair, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "repairs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Repair> editRepair(@PathVariable(value = "id") Long id, @RequestBody RepairDTO repairDTO) {
        Repair repair = repairService.findOne(id);
        if (repairDTO.getDate() != null)
            repair.setDate(repairDTO.getDate());
        if (repairDTO.getMileage() != null)
            repair.setMileage(repairDTO.getMileage());
        if (!StringUtils.isEmpty(repairDTO.getDescription()))
            repair.setDescription(repairDTO.getDescription());
        if (repairDTO.getExploitationType() != null)
            repair.setExploitationType(exploitationTypeService.findOneByValue(repairDTO.getExploitationType()));
        if (repairDTO.getCost() != null)
            repair.setCost(repairDTO.getCost());
        repairService.save(repair);
        return new ResponseEntity<>(repair, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "repairs/{id}", method = RequestMethod.GET)
    public ResponseEntity<Repair> getRepair(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(repairService.findOne(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/repairs", method = RequestMethod.GET)
    public ResponseEntity<List<Repair>> getAllRepairs(@PathVariable(value = "carId") Long carId) {
        return new ResponseEntity<>(repairService.findAllByCarId(carId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "repairs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Repair> deleteRepair(@PathVariable(value = "id") Long id) {
        repairService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}