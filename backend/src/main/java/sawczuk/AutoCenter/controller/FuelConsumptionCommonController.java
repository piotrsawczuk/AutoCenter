package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.model.dto.FuelConsumptionAverageResponse;
import sawczuk.AutoCenter.service.FuelConsumptionService;

import java.util.List;

@Controller
@RequestMapping(value = "fuel-consumption/avg")
@RequiredArgsConstructor
public class FuelConsumptionCommonController {

    private final FuelConsumptionService fuelConsumptionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FuelConsumptionAverageResponse>> fuelConsumptionAveragesByCarApiId(@RequestParam(value = "carApiId") Long carApiId) {
        return ResponseEntity.ok(fuelConsumptionService.fuelConsumptionAveragesByCarApiId(carApiId));
    }
}
