package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.Repair;
import sawczuk.AutoCenter.model.dto.RepairRequest;
import sawczuk.AutoCenter.model.dto.RepairResponse;
import sawczuk.AutoCenter.model.dto.RepairTotalCostResponse;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.ExploitationTypeService;
import sawczuk.AutoCenter.service.RepairService;

import java.util.List;

@Controller
@RequestMapping(value = "cars/{carId}/repairs")
@RequiredArgsConstructor
public class RepairController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private final RepairService repairService;
    private final CarService carService;
    private final ExploitationTypeService exploitationTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<RepairResponse>> getAllRepairs(
            @PathVariable(value = "carId") Long carId,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "date", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.mapAll(repairService.findAllByCarId(carId, pageable), RepairResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RepairResponse> getRepair(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.map(repairService.findByIdAndCarId(id, carId), RepairResponse.class));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RepairResponse> saveRepair(
            @PathVariable(value = "carId") Long carId,
            @RequestBody RepairRequest repairRequest) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        Car car = carService.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        Repair repair = new Repair();
        DtoEntityMapper.map(repairRequest, repair);
        repair.setExploitationType(exploitationTypeService.findByValue(repairRequest.getExploitationType()));
        repair.setCar(car);
        repairService.save(repair);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(repair, RepairResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RepairResponse> editRepair(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId,
            @RequestBody RepairRequest repairRequest) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        Repair repair = repairService.findByIdAndCarId(id, carId);
        if (repair == null) {
            throw new ResourceNotFoundException("Repair", "id", id, "carId", carId);
        }

        DtoEntityMapper.map(repairRequest, repair);
        if (repairRequest.getExploitationType() != null) {
            repair.setExploitationType(exploitationTypeService.findByValue(repairRequest.getExploitationType()));
        }
        repairService.save(repair);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(repair, RepairResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRepair(@PathVariable(value = "id") Long id, @PathVariable(value = "carId") Long carId)
            throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        repairService.deleteByIdAndCarId(id, carId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/total-cost", method = RequestMethod.GET)
    public ResponseEntity<List<RepairTotalCostResponse>> repairsTotalCostByCarId(@PathVariable(value = "carId") Long carId)
            throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.mapAll(repairService.repairsTotalCostByCarId(carId), RepairTotalCostResponse.class));
    }
}