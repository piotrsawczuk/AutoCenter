package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.CarDetailRequest;
import sawczuk.AutoCenter.model.dto.CarDetailResponse;
import sawczuk.AutoCenter.service.CarDetailService;

@Controller
@RequiredArgsConstructor
@RequestMapping("cars/{carId}/details")
public class CarDetailController {

    private final CarDetailService carDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CarDetailResponse> getCarDetail(@PathVariable("carId") Long carId)
            throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(carDetailService.findByCarId(carId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarDetailResponse> saveCarDetail(
            @PathVariable("carId") Long carId,
            @RequestBody CarDetailRequest carDetailRequest) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(carDetailService.save(carDetailRequest, carId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CarDetailResponse> editCarDetail(
            @PathVariable("carId") Long carId,
            @RequestBody CarDetailRequest carDetailRequest) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(carDetailService.update(carDetailRequest, carId));
    }
}
