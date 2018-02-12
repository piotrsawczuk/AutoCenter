package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        //logged in user
        userCar.setUser(userService.findByUsername("piotr"));
        userCarService.save(userCar);
        return new ResponseEntity<>(userCar, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserCar> editCar(@PathVariable Long id, @RequestBody UserCarDTO userCarDTO) {
        UserCar userCar = userCarService.findOne(id);
        if (userCarDTO.getCarApiId() != null)
            userCar.setCarApiId(userCarDTO.getCarApiId());
        userCarService.save(userCar);
        return new ResponseEntity<>(userCar, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<List<UserCar>> getAllCars() {
        //logged in user
        Long userId = userService.findByUsername("piotr").getId();
        return new ResponseEntity<>(userCarService.findAllByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserCar> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(userCarService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserCar> deleteCar(@PathVariable Long id) {
        userCarService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
//TODO change userService for UserUtil.findLoggedInUsername