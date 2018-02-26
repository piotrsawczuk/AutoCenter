package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.FuelEconomy;
import sawczuk.AutoCenter.model.FuelEconomyAverage;
import sawczuk.AutoCenter.model.dto.FuelEconomyDTO;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.DrivingTypeService;
import sawczuk.AutoCenter.service.FuelEconomyService;
import sawczuk.AutoCenter.service.FuelTypeService;

import java.util.List;

@Controller
public class FuelEconomyController {

    private FuelEconomyService fuelEconomyService;
    private CarService carService;
    private DrivingTypeService drivingTypeService;
    private FuelTypeService fuelTypeService;

    @Autowired
    public FuelEconomyController(FuelEconomyService fuelEconomyService, CarService carService, DrivingTypeService drivingTypeService, FuelTypeService fuelTypeService) {
        this.fuelEconomyService = fuelEconomyService;
        this.carService = carService;
        this.drivingTypeService = drivingTypeService;
        this.fuelTypeService = fuelTypeService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/fuel-economy", method = RequestMethod.POST)
    public ResponseEntity<FuelEconomy> saveFuelEconomy(@PathVariable(value = "carId") Long carId, @RequestBody FuelEconomyDTO fuelEconomyDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        Car car = carService.findOne(carId);
        if (car == null) {
            throw new ResourceNotFoundException("Car", "carId", carId);
        }

        FuelEconomy fuelEconomy = new FuelEconomy();
        if (fuelEconomyDTO.getDate() != null)
            fuelEconomy.setDate(fuelEconomyDTO.getDate());
        fuelEconomy.setCar(car);
        fuelEconomy.setDrivingType(drivingTypeService.findOneByValue(fuelEconomyDTO.getDrivingType()));
        fuelEconomy.setFuelType(fuelTypeService.findOneByValue(fuelEconomyDTO.getFuelType()));
        fuelEconomy.setDistanceDriven(fuelEconomyDTO.getDistanceDriven());
        fuelEconomy.setFuelAmountFilled(fuelEconomyDTO.getFuelAmountFilled());
        fuelEconomy.setPricePerLitre(fuelEconomyDTO.getPricePerLitre());
        fuelEconomyService.save(fuelEconomy);
        return new ResponseEntity<>(fuelEconomy, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "fuel-economy/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FuelEconomy> editFuelEconomy(@PathVariable(value = "id") Long id, @RequestBody FuelEconomyDTO fuelEconomyDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        FuelEconomy fuelEconomy = fuelEconomyService.findOne(id);
        if (fuelEconomy == null) {
            throw new ResourceNotFoundException("Fuel economy", "id", id);
        }

        if (fuelEconomyDTO.getDate() != null)
            fuelEconomy.setDate(fuelEconomyDTO.getDate());
        if (fuelEconomyDTO.getDrivingType() != null)
            fuelEconomy.setDrivingType(drivingTypeService.findOneByValue(fuelEconomyDTO.getDrivingType()));
        if (fuelEconomyDTO.getFuelType() != null)
            fuelEconomy.setFuelType(fuelTypeService.findOneByValue(fuelEconomyDTO.getFuelType()));
        if (fuelEconomyDTO.getDistanceDriven() != null)
            fuelEconomy.setDistanceDriven(fuelEconomyDTO.getDistanceDriven());
        if (fuelEconomyDTO.getFuelAmountFilled() != null)
            fuelEconomy.setFuelAmountFilled(fuelEconomyDTO.getFuelAmountFilled());
        if (fuelEconomyDTO.getPricePerLitre() != null)
            fuelEconomy.setPricePerLitre(fuelEconomyDTO.getPricePerLitre());
        fuelEconomyService.save(fuelEconomy);
        return new ResponseEntity<>(fuelEconomy, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "fuel-economy/{id}", method = RequestMethod.GET)
    public ResponseEntity<FuelEconomy> getFuelEconomy(@PathVariable(value = "id") Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        return new ResponseEntity<>(fuelEconomyService.findOne(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/fuel-economy", method = RequestMethod.GET)
    public ResponseEntity<List<FuelEconomy>> getAllFuelEconomy(@PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return new ResponseEntity<>(fuelEconomyService.findAllByCarId(carId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "fuel-economy/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FuelEconomy> deleteFuelEconomy(@PathVariable(value = "id") Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        fuelEconomyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "cars/{carId}/fuel-economy/avg", method = RequestMethod.GET)
    public ResponseEntity<List<FuelEconomyAverage>> fuelEconomyAveragesByCarId(@PathVariable(value = "carId") Long carId) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return new ResponseEntity<>(fuelEconomyService.fuelEconomyAveragesByCarId(carId), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "fuel-economy/avg", method = RequestMethod.GET)
    public ResponseEntity<List<FuelEconomyAverage>> fuelEconomyAveragesByCarApiId(@RequestParam(value = "carApiId") Long carApiId) {
        return new ResponseEntity<>(fuelEconomyService.fuelEconomyAveragesByCarApiId(carApiId), HttpStatus.OK);
    }
}