package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.model.UserCar;
import sawczuk.AutoCenter.model.dto.UserCarDTO;
import sawczuk.AutoCenter.service.UserCarService;
import sawczuk.AutoCenter.service.UserService;

import java.util.List;

@Controller
public class UserCarController {

    private UserCarService userCarService;
    private UserService userService;

//    @Autowired
//    public UserCarController(UserCarService userCarService) {
//        this.userCarService = userCarService;
//    }

    @Autowired
    public UserCarController(UserCarService userCarService, UserService userService) {
        this.userCarService = userCarService;
        this.userService = userService;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity<UserCar> saveCar(@RequestBody UserCarDTO userCarDTO) {
        UserCar userCar = new UserCar();
        userCar.setCarApiId(userCarDTO.getCarApiId());
        //
        userCar.setUser(userService.findByUsername(userCarDTO.getUsername()));
        userCarService.save(userCar);
        return new ResponseEntity<>(userCar, HttpStatus.OK);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<List<UserCar>> getAllCars() {
        //
        Long id = userService.findByUsername("piotr").getId();
        return new ResponseEntity<>(userCarService.findAllByUserId(id), HttpStatus.OK);
    }

}
//TODO change userService for UserUtil.findLoggedInUsername