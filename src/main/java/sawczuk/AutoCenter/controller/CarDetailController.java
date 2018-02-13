package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.model.CarDetail;
import sawczuk.AutoCenter.model.dto.CarDetailDTO;
import sawczuk.AutoCenter.service.CarDetailService;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.util.VinChecker;

@Controller
@RequestMapping("cars/{carId}")
public class CarDetailController {

    private CarDetailService carDetailService;
    private CarService carService;

    @Autowired
    public CarDetailController(CarDetailService carDetailService, CarService carService) {
        this.carDetailService = carDetailService;
        this.carService = carService;
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResponseEntity<CarDetail> saveUserCarDetail(@PathVariable("carId") Long carId, @RequestBody CarDetailDTO carDetailDTO) {
        CarDetail carDetail = new CarDetail();
//        if (VinChecker.validate(carDetailDTO.getVin()))
            carDetail.setVin(carDetailDTO.getVin());
        carDetail.setLicencePlateNumber(carDetailDTO.getLicencePlateNumber());
        carDetail.setColor(carDetailDTO.getColor());
        carDetail.setImageUrl(carDetailDTO.getImageUrl());
        carDetail.setCar(carService.findOne(carId));
        carDetailService.save(carDetail);
        return new ResponseEntity<>(carDetail, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/details", method = RequestMethod.PUT)
    public ResponseEntity<CarDetail> editUserCarDetail(@PathVariable("carId") Long carId, @RequestBody CarDetailDTO carDetailDTO) {
        CarDetail carDetail = carDetailService.findOneByCarId(carId);
        if (!StringUtils.isEmpty(carDetailDTO.getVin()))
//            if (VinChecker.validate(carDetailDTO.getVin()))
                carDetail.setVin(carDetailDTO.getVin());
        if (!StringUtils.isEmpty(carDetailDTO.getLicencePlateNumber()))
            carDetail.setLicencePlateNumber(carDetailDTO.getLicencePlateNumber());
        if (!StringUtils.isEmpty(carDetailDTO.getColor()))
            carDetail.setColor(carDetailDTO.getColor());
        if (!StringUtils.isEmpty(carDetailDTO.getImageUrl()))
            carDetail.setImageUrl(carDetailDTO.getImageUrl());
        carDetailService.save(carDetail);
        return new ResponseEntity<>(carDetail, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<CarDetail> getUserCarDetail(@PathVariable("carId") Long carId) {
        return new ResponseEntity<>(carDetailService.findOneByCarId(carId), HttpStatus.OK);
    }
}
//TODO uncomment after tests