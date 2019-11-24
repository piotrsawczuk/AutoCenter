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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.FuelConsumptionAverageResponse;
import sawczuk.AutoCenter.model.dto.FuelConsumptionRequest;
import sawczuk.AutoCenter.model.dto.FuelConsumptionResponse;
import sawczuk.AutoCenter.service.FuelConsumptionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "cars/{carId}/fuel-consumption")
public class FuelConsumptionController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private final FuelConsumptionService fuelConsumptionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<FuelConsumptionResponse>> getAllFuelConsumption(
            @PathVariable(value = "carId") Long carId,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "date", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(fuelConsumptionService.findAllByCarId(carId, pageable));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FuelConsumptionResponse> getFuelConsumption(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null || carId == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        return ResponseEntity.ok(fuelConsumptionService.findByIdAndCarId(id, carId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FuelConsumptionResponse> saveFuelConsumption(
            @PathVariable(value = "carId") Long carId,
            @RequestBody FuelConsumptionRequest fuelConsumptionRequest)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fuelConsumptionService.save(fuelConsumptionRequest, carId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FuelConsumptionResponse> editFuelConsumption(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId,
            @RequestBody FuelConsumptionRequest fuelConsumptionRequest)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null || carId == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(fuelConsumptionService.update(fuelConsumptionRequest, id, carId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFuelConsumption(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null || carId == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        fuelConsumptionService.deleteByIdAndCarId(id, carId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/avg", method = RequestMethod.GET)
    public ResponseEntity<List<FuelConsumptionAverageResponse>> fuelConsumptionAveragesByCarId(@PathVariable(value = "carId") Long carId)
            throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(fuelConsumptionService.fuelConsumptionAveragesByCarId(carId));
    }
}