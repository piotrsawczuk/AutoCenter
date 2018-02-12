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
import sawczuk.AutoCenter.model.UserCarDetail;
import sawczuk.AutoCenter.model.dto.UserCarDetailDTO;
import sawczuk.AutoCenter.service.UserCarDetailService;
import sawczuk.AutoCenter.service.UserCarService;

@Controller
@RequestMapping("cars/{carId}")
public class UserCarDetailController {

    private UserCarDetailService userCarDetailService;
    private UserCarService userCarService;

    @Autowired
    public UserCarDetailController(UserCarDetailService userCarDetailService, UserCarService userCarService) {
        this.userCarDetailService = userCarDetailService;
        this.userCarService = userCarService;
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public ResponseEntity<UserCarDetail> saveUserCarDetail(@PathVariable("carId") Long carId, @RequestBody UserCarDetailDTO userCarDetailDTO) {
        UserCarDetail userCarDetail = new UserCarDetail();
        userCarDetail.setVin(userCarDetailDTO.getVin());
        userCarDetail.setLicencePlateNumber(userCarDetailDTO.getLicencePlateNumber());
        userCarDetail.setColor(userCarDetailDTO.getColor());
        userCarDetail.setImageUrl(userCarDetailDTO.getImageUrl());
        userCarDetail.setUserCar(userCarService.findOne(carId));
        userCarDetailService.save(userCarDetail);
        return new ResponseEntity<>(userCarDetail, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/details", method = RequestMethod.PUT)
    public ResponseEntity<UserCarDetail> editUserCarDetail(@PathVariable("carId") Long carId, @RequestBody UserCarDetailDTO userCarDetailDTO) {
        UserCarDetail userCarDetail = userCarDetailService.findOneByUserCarId(carId);
        if (!StringUtils.isEmpty(userCarDetailDTO.getVin()))
            userCarDetail.setVin(userCarDetailDTO.getVin());
        if (!StringUtils.isEmpty(userCarDetailDTO.getLicencePlateNumber()))
            userCarDetail.setLicencePlateNumber(userCarDetailDTO.getLicencePlateNumber());
        if (!StringUtils.isEmpty(userCarDetailDTO.getColor()))
            userCarDetail.setColor(userCarDetailDTO.getColor());
        if (!StringUtils.isEmpty(userCarDetailDTO.getImageUrl()))
            userCarDetail.setImageUrl(userCarDetailDTO.getImageUrl());
        userCarDetailService.save(userCarDetail);
        return new ResponseEntity<>(userCarDetail, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<UserCarDetail> getUserCarDetail(@PathVariable("carId") Long carId) {
        return new ResponseEntity<>(userCarDetailService.findOneByUserCarId(carId), HttpStatus.OK);
    }

}