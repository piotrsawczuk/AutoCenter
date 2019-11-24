package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.service.FuelTypeService;

@Controller
@RequestMapping(value = "/fuel-types")
@RequiredArgsConstructor
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getFuelType(
            @RequestParam(value = "value", required = false) Integer value,
            @RequestParam(value = "fuel_type", required = false) String fuelType) throws ResourceNotFoundException {
        if (value != null) {
            return ResponseEntity.ok(fuelTypeService.findByValue(value));
        } else if (!StringUtils.isEmpty(fuelType)) {
            return ResponseEntity.ok(fuelTypeService.findByFuelTypeIgnoreCase(fuelType));
        } else {
            return ResponseEntity.ok(fuelTypeService.findAll());
        }
    }
}
