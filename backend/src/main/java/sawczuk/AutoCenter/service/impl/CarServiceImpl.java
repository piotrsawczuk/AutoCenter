package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.service.CarService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAllByUserId(Long userId) {
        return carRepository.findAllByUserId(userId);
    }

    @Override
    public Page<Car> findAllByUserId(Long userId, Pageable pageable) {
        return carRepository.findAllByUserId(userId, pageable);
    }
}
