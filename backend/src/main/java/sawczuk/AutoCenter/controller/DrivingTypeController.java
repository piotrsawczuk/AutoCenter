package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.service.DrivingTypeService;

@Controller
@RequestMapping(value = "/driving-types")
@RequiredArgsConstructor
public class DrivingTypeController {

    private final DrivingTypeService drivingTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getDrivingType(
            @RequestParam(value = "value", required = false) Integer value,
            @RequestParam(value = "driving_type", required = false) String drivingType) throws ResourceNotFoundException {
        if (value != null) {
            return ResponseEntity.ok(drivingTypeService.findByValue(value));
        } else if (!StringUtils.isEmpty(drivingType)) {
            return ResponseEntity.ok(drivingTypeService.findByDrivingTypeIgnoreCase(drivingType));
        } else {
            return ResponseEntity.ok(drivingTypeService.findAll());
        }
    }
}
