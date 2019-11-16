package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.CarDetail;
import sawczuk.AutoCenter.model.dto.CarDetailDTO;
import sawczuk.AutoCenter.service.CarDetailService;
import sawczuk.AutoCenter.service.CarService;

@Controller
@RequiredArgsConstructor
@RequestMapping("cars/{carId}")
public class CarDetailController {

    private final CarDetailService carDetailService;
    private final CarService carService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResponseEntity<CarDetail> saveCarDetail(@PathVariable("carId") Long carId, @RequestBody CarDetailDTO carDetailDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        Car car = carService.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        CarDetail carDetail = new CarDetail();
//        if (VinChecker.validate(carDetailDTO.getVin()))
        carDetail.setVin(carDetailDTO.getVin());
        carDetail.setLicencePlateNumber(carDetailDTO.getLicencePlateNumber());
        carDetail.setColor(carDetailDTO.getColor());
        carDetail.setImageUrl(carDetailDTO.getImageUrl());
        carDetail.setCar(car);
        carDetailService.save(carDetail);
        return new ResponseEntity<>(carDetail, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/details", method = RequestMethod.PUT)
    public ResponseEntity<CarDetail> editCarDetail(@PathVariable("carId") Long carId, @RequestBody CarDetailDTO carDetailDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        CarDetail carDetail = carDetailService.findByCarId(carId);
        if (carDetail == null) {
            throw new ResourceNotFoundException("Car detail", "carId", carId);
        }

//      if (carDetailDTO.getVin() == null || VinChecker.validate(carDetailDTO.getVin()))
        carDetail.setVin(carDetailDTO.getVin());
        carDetail.setLicencePlateNumber(carDetailDTO.getLicencePlateNumber());
        carDetail.setColor(carDetailDTO.getColor());
        carDetail.setImageUrl(carDetailDTO.getImageUrl());
        carDetailService.save(carDetail);
        return new ResponseEntity<>(carDetail, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<CarDetail> getCarDetail(@PathVariable("carId") Long carId) throws InvalidRequestParameterException {
        if (carId == null) {
            throw new InvalidRequestParameterException("carId", carId);
        }
        return new ResponseEntity<>(carDetailService.findByCarId(carId), HttpStatus.OK);
    }
}
//TODO uncomment after tests