package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.service.FuelTypeService;

@Controller
@RequiredArgsConstructor
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/fuel-types", method = RequestMethod.GET)
    public ResponseEntity<?> getFuelType(@RequestParam(value = "value", required = false) Integer value,
                                         @RequestParam(value = "fuel_type", required = false) String fuelType) throws ResourceNotFoundException {
        if (value != null)
            return new ResponseEntity<>(fuelTypeService.findByValue(value), HttpStatus.OK);
        else if (!StringUtils.isEmpty(fuelType))
            return new ResponseEntity<>(fuelTypeService.findByFuelTypeIgnoreCase(fuelType), HttpStatus.OK);
        else
            return new ResponseEntity<>(fuelTypeService.findAll(), HttpStatus.OK);
    }
}
