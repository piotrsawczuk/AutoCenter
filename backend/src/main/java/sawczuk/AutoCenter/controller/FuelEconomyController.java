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
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.dto.FuelEconomyAverageResponse;
import sawczuk.AutoCenter.model.dto.FuelEconomyRequest;
import sawczuk.AutoCenter.model.dto.FuelEconomyResponse;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.DrivingTypeService;
import sawczuk.AutoCenter.service.FuelEconomyService;
import sawczuk.AutoCenter.service.FuelTypeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "cars/{carId}/fuel-economy")
public class FuelEconomyController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    private final FuelEconomyService fuelEconomyService;
    private final CarService carService;
    private final DrivingTypeService drivingTypeService;
    private final FuelTypeService fuelTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<FuelEconomyResponse>> getAllFuelEconomy(
            @PathVariable(value = "carId") Long carId,
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "date", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.mapAll(fuelEconomyService.findAllByCarId(carId, pageable), FuelEconomyResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FuelEconomyResponse> getFuelEconomy(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.map(fuelEconomyService.findByIdAndCarId(id, carId), FuelEconomyResponse.class));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FuelEconomyResponse> saveFuelEconomy(
            @PathVariable(value = "carId") Long carId,
            @RequestBody FuelEconomyRequest fuelEconomyRequest)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        Car car = carService.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));

        FuelEconomy fuelEconomy = new FuelEconomy();
        DtoEntityMapper.map(fuelEconomyRequest, fuelEconomy);
        fuelEconomy.setDrivingType(drivingTypeService.findByValue(fuelEconomyRequest.getDrivingType()));
        fuelEconomy.setFuelType(fuelTypeService.findByValue(fuelEconomyRequest.getFuelType()));
        fuelEconomy.setCar(car);
        fuelEconomyService.save(fuelEconomy);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(fuelEconomy, FuelEconomyResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FuelEconomyResponse> editFuelEconomy(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId,
            @RequestBody FuelEconomyRequest fuelEconomyRequest)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        FuelEconomy fuelEconomy = fuelEconomyService.findByIdAndCarId(id, carId);
        if (fuelEconomy == null) {
            throw new ResourceNotFoundException("Fuel economy", "id", id, "carId", carId);
        }

        DtoEntityMapper.map(fuelEconomyRequest, fuelEconomy);
        if (fuelEconomyRequest.getDrivingType() != null) {
            fuelEconomy.setDrivingType(drivingTypeService.findByValue(fuelEconomyRequest.getDrivingType()));
        }
        if (fuelEconomyRequest.getFuelType() != null) {
            fuelEconomy.setFuelType(fuelTypeService.findByValue(fuelEconomyRequest.getFuelType()));
        }
        fuelEconomyService.save(fuelEconomy);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(fuelEconomy, FuelEconomyResponse.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFuelEconomy(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id, "carId", carId);
        }
        fuelEconomyService.deleteByIdAndCarId(id, carId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/avg", method = RequestMethod.GET)
    public ResponseEntity<List<FuelEconomyAverageResponse>> fuelEconomyAveragesByCarId(@PathVariable(value = "carId") Long carId)
            throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.mapAll(
                fuelEconomyService.fuelEconomyAveragesByCarId(carId),
                FuelEconomyAverageResponse.class));
    }
}