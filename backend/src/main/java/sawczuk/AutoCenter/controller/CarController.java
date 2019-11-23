package sawczuk.AutoCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.CarRequest;
import sawczuk.AutoCenter.model.dto.CarResponse;
import sawczuk.AutoCenter.service.CarService;

@Controller
@RequestMapping(value = "/cars")
@RequiredArgsConstructor
public class CarController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final CarService carService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<CarResponse>> getAllCars(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(carService.findAllByLoggedInUser(pageable));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        return ResponseEntity.ok(carService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarRequest carRequest) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carRequest));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CarResponse> editCar(@PathVariable Long id, @RequestBody CarRequest carRequest)
            throws InvalidRequestParameterException, ResourceNotFoundException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.update(carRequest, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCar(@PathVariable Long id) throws InvalidRequestParameterException {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}