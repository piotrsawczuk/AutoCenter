package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sawczuk.AutoCenter.model.dto.RepairTotalCostResponse;
import sawczuk.AutoCenter.service.RepairService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

import java.util.List;

@Controller
@RequestMapping(value = "repairs/total-cost")
@RequiredArgsConstructor
public class RepairCommonController {

    private final RepairService repairService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RepairTotalCostResponse>> repairsTotalCostByCarApiId(@RequestParam(value = "carApiId") Long carApiId) {
        return ResponseEntity.ok(DtoEntityMapper.mapAll(repairService.repairsTotalCostByCarApiId(carApiId), RepairTotalCostResponse.class));
    }
}
