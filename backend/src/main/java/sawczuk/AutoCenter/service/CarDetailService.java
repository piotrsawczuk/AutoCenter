package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.CarDetail;

import java.util.Optional;

public interface CarDetailService {
    void save(CarDetail carDetail);

    Optional<CarDetail> findByCarId(Long carId);
}
