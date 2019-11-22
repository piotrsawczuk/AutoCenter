package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.CarRequest;
import sawczuk.AutoCenter.model.dto.CarResponse;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.repository.UserRepository;
import sawczuk.AutoCenter.security.LoggedInUserProvider;
import sawczuk.AutoCenter.service.CarService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Override
    public CarResponse save(CarRequest carRequest) throws ResourceNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", LoggedInUserProvider.findLoggedInUsername()));
        Car car = new Car();
        DtoEntityMapper.map(carRequest, car);
        car.setUser(user);
        carRepository.save(car);
        return DtoEntityMapper.map(car, CarResponse.class);
    }

    @Override
    public CarResponse update(CarRequest carRequest, Long id) throws ResourceNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));
        DtoEntityMapper.map(carRequest, car);
        carRepository.save(car);
        return DtoEntityMapper.map(car, CarResponse.class);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarResponse findById(Long id) throws ResourceNotFoundException {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));
        return DtoEntityMapper.map(car, CarResponse.class);
    }

    @Override
    public Page<CarResponse> findAllByUser(Pageable pageable) throws ResourceNotFoundException {
        Long userId = userRepository.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User ID", "username", LoggedInUserProvider.findLoggedInUsername()))
                .getId();
        return DtoEntityMapper.mapAll(carRepository.findAllByUserId(userId, pageable), CarResponse.class);
    }
}
