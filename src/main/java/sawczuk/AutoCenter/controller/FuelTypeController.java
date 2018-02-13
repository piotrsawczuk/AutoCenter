package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.model.FuelType;
import sawczuk.AutoCenter.service.FuelTypeService;

import java.util.List;

@Controller
public class FuelTypeController {

    private FuelTypeService fuelTypeService;

    @Autowired
    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    @RequestMapping(value = "/fuel-types", method = RequestMethod.GET)
    public ResponseEntity<?> getFuelType(@RequestParam(value = "value", required = false) Integer value,
                                         @RequestParam(value = "fuel_type", required = false) String fuelType) {
        if (value != null)
            return new ResponseEntity<>(fuelTypeService.findOneByValue(value), HttpStatus.OK);
        else
        if (!StringUtils.isEmpty(fuelType))
            return new ResponseEntity<>(fuelTypeService.findOneByFuelTypeIgnoreCase(fuelType), HttpStatus.OK);
        else
            return new ResponseEntity<>(fuelTypeService.findAll(), HttpStatus.OK);
    }
}
