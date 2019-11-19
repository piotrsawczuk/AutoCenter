package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.controller.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.model.dto.FuelEconomyAverageResponse;
import sawczuk.AutoCenter.service.FuelEconomyService;

import java.util.List;

@Controller
@RequestMapping(value = "fuel-economy/avg")
@RequiredArgsConstructor
public class FuelEconomyCommonController {

    private final FuelEconomyService fuelEconomyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FuelEconomyAverageResponse>> fuelEconomyAveragesByCarApiId(@RequestParam(value = "carApiId") Long carApiId) {
        return ResponseEntity.ok(DtoEntityMapper.mapAll(
                fuelEconomyService.fuelEconomyAveragesByCarApiId(carApiId),
                FuelEconomyAverageResponse.class));
    }
}
