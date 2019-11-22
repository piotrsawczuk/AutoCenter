package sawczuk.AutoCenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import sawczuk.AutoCenter.model.Car;

import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    Page<Car> findAllByUserId(Long userId, Pageable pageable);
}
