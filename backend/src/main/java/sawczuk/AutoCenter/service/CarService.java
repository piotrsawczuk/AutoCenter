package sawczuk.AutoCenter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.CarRequest;
import sawczuk.AutoCenter.model.dto.CarResponse;

public interface CarService {
    CarResponse save(CarRequest carRequest) throws ResourceNotFoundException;

    CarResponse update(CarRequest carRequest, Long id) throws ResourceNotFoundException;

    void deleteById(Long id);

    CarResponse findById(Long id) throws ResourceNotFoundException;

    Page<CarResponse> findAllByUser(Pageable pageable) throws ResourceNotFoundException;
}
