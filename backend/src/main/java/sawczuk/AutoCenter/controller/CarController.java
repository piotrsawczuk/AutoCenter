package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import sawczuk.AutoCenter.model.dto.CarRequest;
import sawczuk.AutoCenter.model.dto.CarResponse;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.util.UserUtils;

@Controller
@RequiredArgsConstructor
public class CarController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final CarService carService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarRequest carDTO) throws ResourceNotFoundException {
        User user = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", UserUtils.findLoggedInUsername()));
        Car car = new Car();
        car.setCarApiId(carDTO.getCarApiId());
        car.setCarName(carDTO.getCarName());
        car.setUser(user);
        carService.save(car);
        return new ResponseEntity<>(convertToDto(car), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CarResponse> editCar(@PathVariable Long id, @RequestBody CarRequest carDTO)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        Car car = carService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));
        if (carDTO.getCarApiId() != null && carDTO.getCarName() != null) {
            car.setCarApiId(carDTO.getCarApiId());
            car.setCarName(carDTO.getCarName());
        }
        carService.save(car);
        return new ResponseEntity<>(convertToDto(car), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<Page<CarResponse>> getAllCars(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE)
            Pageable pageable) throws ResourceNotFoundException {
        Long userId = userService.findByUsernameIgnoreCase(UserUtils.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User ID", "username", UserUtils.findLoggedInUsername()))
                .getId();
        return new ResponseEntity<>(carService.findAllByUserId(userId, pageable).map(this::convertToDto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        Car car = carService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));
        return new ResponseEntity<>(convertToDto(car), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CarResponse convertToDto(Car car) {
        return modelMapper.map(car, CarResponse.class);
    }
}