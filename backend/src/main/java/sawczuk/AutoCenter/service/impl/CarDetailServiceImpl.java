package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.model.CarDetail;
import sawczuk.AutoCenter.repository.CarDetailRepository;
import sawczuk.AutoCenter.service.CarDetailService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarDetailServiceImpl implements CarDetailService {

    private final CarDetailRepository carDetailRepository;

    @Override
    public void save(CarDetail carDetail) {
        if (!StringUtils.isEmpty(carDetail.getVin()))
            carDetail.setVin(carDetail.getVin().toUpperCase());
        if (!StringUtils.isEmpty(carDetail.getLicencePlateNumber()))
            carDetail.setLicencePlateNumber(carDetail.getLicencePlateNumber().toUpperCase());
        carDetailRepository.save(carDetail);
    }

    @Override
    public Optional<CarDetail> findByCarId(Long carId) {
        return carDetailRepository.findByCarId(carId);
    }

}
