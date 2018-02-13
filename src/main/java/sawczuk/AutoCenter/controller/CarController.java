package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.dto.CarDTO;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.UserService;

import java.util.List;

@Controller
public class CarController {

    private CarService carService;
    private UserService userService;

//    @Autowired
//    public CarController(CarService carService) {
//        this.carService = carService;
//    }

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity<Car> saveCar(@RequestBody CarDTO carDTO) {
        Car car = new Car();
        car.setCarApiId(carDTO.getCarApiId());
        //logged in user
        car.setUser(userService.findByUsernameIgnoreCase("piotr"));
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Car> editCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        Car car = carService.findOne(id);
        if (carDTO.getCarApiId() != null)
            car.setCarApiId(carDTO.getCarApiId());
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllCars() {
        //logged in user
        Long userId = userService.findByUsernameIgnoreCase("piotr").getId();
        return new ResponseEntity<>(carService.findAllByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
//TODO change userService for UserUtil.findLoggedInUsername