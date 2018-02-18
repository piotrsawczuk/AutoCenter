package sawczuk.AutoCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.CarDTO;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.util.UserUtils;

import java.util.List;

@Controller
public class CarController {

    private CarService carService;
    private UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity<Car> saveCar(@RequestBody CarDTO carDTO) throws ResourceNotFoundException {
        User user = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername());
        if (user == null) {
            throw new ResourceNotFoundException("User", "username", UserUtils.findLoggedInUsername());
        }
        Car car = new Car();
        car.setCarApiId(carDTO.getCarApiId());
        car.setUser(user);
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Car> editCar(@PathVariable Long id, @RequestBody CarDTO carDTO) throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        Car car = carService.findOne(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car", "id", id);
        }
        if (carDTO.getCarApiId() != null)
            car.setCarApiId(carDTO.getCarApiId());
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllCars() throws ResourceNotFoundException {
        Long userId = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername()).getId();
        if (userId == null) {
            throw new ResourceNotFoundException("User ID", "username", UserUtils.findLoggedInUsername());
        }
        return new ResponseEntity<>(carService.findAllByUserId(userId), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCar(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        return new ResponseEntity<>(carService.findOne(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}