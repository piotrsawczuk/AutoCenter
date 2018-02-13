package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.model.FuelEconomy;
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

    @RequestMapping(value = "cars/{carId}/fuel-economy", method = RequestMethod.POST)
    public ResponseEntity<FuelEconomy> saveFuelEconomy(@PathVariable(value = "carId") Long carId, @RequestBody FuelEconomyDTO fuelEconomyDTO) {
        FuelEconomy fuelEconomy = new FuelEconomy();
        if (fuelEconomyDTO.getDate() != null)
            fuelEconomy.setDate(fuelEconomyDTO.getDate());
        fuelEconomy.setCar(carService.findOne(carId));
        fuelEconomy.setDrivingType(drivingTypeService.findOneByValue(fuelEconomyDTO.getDrivingType()));
        fuelEconomy.setFuelType(fuelTypeService.findOneByValue(fuelEconomyDTO.getFuelType()));
        fuelEconomy.setDistanceDriven(fuelEconomyDTO.getDistanceDriven());
        fuelEconomy.setFuelAmountFilled(fuelEconomyDTO.getFuelAmountFilled());
        fuelEconomy.setPricePerLitre(fuelEconomyDTO.getPricePerLitre());
        fuelEconomyService.save(fuelEconomy);
        return new ResponseEntity<>(fuelEconomy, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "fuel-economy/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FuelEconomy> editFuelEconomy(@PathVariable(value = "id") Long id, @RequestBody FuelEconomyDTO fuelEconomyDTO) {
        FuelEconomy fuelEconomy = fuelEconomyService.findOne(id);
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

    @RequestMapping(value = "fuel-economy/{id}", method = RequestMethod.GET)
    public ResponseEntity<FuelEconomy> getFuelEconomy(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(fuelEconomyService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "cars/{carId}/fuel-economy", method = RequestMethod.GET)
    public ResponseEntity<List<FuelEconomy>> getAllFuelEconomy(@PathVariable(value = "carId") Long carId) {
        return new ResponseEntity<>(fuelEconomyService.findAllByCarId(carId), HttpStatus.OK);
    }

    @RequestMapping(value = "fuel-economy/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<FuelEconomy> deleteFuelEconomy(@PathVariable(value = "id") Long id) {
        fuelEconomyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}