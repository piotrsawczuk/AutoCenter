package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.controller.mapper.DtoEntityMapper;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.CarDetail;
import sawczuk.AutoCenter.model.dto.CarDetailRequest;
import sawczuk.AutoCenter.model.dto.CarDetailResponse;
import sawczuk.AutoCenter.service.CarDetailService;
import sawczuk.AutoCenter.service.CarService;

@Controller
@RequiredArgsConstructor
@RequestMapping("cars/{carId}/details")
public class CarDetailController {

    private final CarDetailService carDetailService;
    private final CarService carService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CarDetailResponse> getCarDetail(@PathVariable("carId") Long carId)
            throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return ResponseEntity.ok(DtoEntityMapper.map(carDetailService.findByCarId(carId).orElse(null), CarDetailResponse.class));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarDetailResponse> saveCarDetail(
            @PathVariable("carId") Long carId,
            @RequestBody CarDetailRequest carDetailRequest) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        Car car = carService.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        CarDetail carDetail = new CarDetail();
        DtoEntityMapper.mapWithNulls(carDetailRequest, carDetail);
        carDetail.setCar(car);
        carDetailService.save(carDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(carDetail, CarDetailResponse.class));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CarDetailResponse> editCarDetail(
            @PathVariable("carId") Long carId,
            @RequestBody CarDetailRequest carDetailRequest) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        CarDetail carDetail = carDetailService.findByCarId(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car detail", "carId", carId));
        DtoEntityMapper.mapWithNulls(carDetailRequest, carDetail);
        carDetailService.save(carDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoEntityMapper.map(carDetail, CarDetailResponse.class));
    }
}
//TODO add condition if (carDetailRequest.getVin() == null || VinChecker.validate(carDetailRequest.getVin()))