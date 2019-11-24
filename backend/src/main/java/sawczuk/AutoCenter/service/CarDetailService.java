package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.CarDetailRequest;
import sawczuk.AutoCenter.model.dto.CarDetailResponse;

public interface CarDetailService {
    CarDetailResponse findByCarId(Long carId);

    CarDetailResponse save(CarDetailRequest carDetail, Long carId) throws ResourceNotFoundException;

    CarDetailResponse update(CarDetailRequest carDetail, Long carId) throws ResourceNotFoundException;
}
