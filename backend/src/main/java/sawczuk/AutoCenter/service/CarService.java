package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    void save(Car car);

    void deleteById(Long id);

    Optional<Car> findById(Long id);

    List<Car> findAllByUserId(Long userId);

    Page<Car> findAllByUserId(Long userId, Pageable pageable);
}
