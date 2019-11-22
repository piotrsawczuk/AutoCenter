package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.Car;
import sawczuk.AutoCenter.model.CarDetail;
import sawczuk.AutoCenter.model.dto.CarDetailRequest;
import sawczuk.AutoCenter.model.dto.CarDetailResponse;
import sawczuk.AutoCenter.repository.CarDetailRepository;
import sawczuk.AutoCenter.repository.CarRepository;
import sawczuk.AutoCenter.service.CarDetailService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

@Service
@RequiredArgsConstructor
public class CarDetailServiceImpl implements CarDetailService {

    private final CarDetailRepository carDetailRepository;
    private final CarRepository carRepository;

    @Override
    public CarDetailResponse findByCarId(Long carId) {
        return carDetailRepository.findByCarId(carId)
                .map(userDetail -> DtoEntityMapper.map(userDetail, CarDetailResponse.class))
                .orElse(null);
    }

    @Override
    public CarDetailResponse save(CarDetailRequest carDetailRequest, Long carId) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", "id", carId));
        carDetailPropertiesToUppercase(carDetailRequest);
        CarDetail carDetail = new CarDetail();
        DtoEntityMapper.mapWithNulls(carDetailRequest, carDetail);
        carDetail.setCar(car);
        carDetailRepository.save(carDetail);
        return DtoEntityMapper.map(carDetail, CarDetailResponse.class);
    }

    @Override
    public CarDetailResponse update(CarDetailRequest carDetailRequest, Long carId) throws ResourceNotFoundException {
        CarDetail carDetail = carDetailRepository.findByCarId(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car detail", "carId", carId));
        carDetailPropertiesToUppercase(carDetailRequest);
        DtoEntityMapper.mapWithNulls(carDetailRequest, carDetail);
        carDetailRepository.save(carDetail);
        return DtoEntityMapper.map(carDetail, CarDetailResponse.class);
    }

    private void carDetailPropertiesToUppercase(CarDetailRequest carDetailRequest) {
        if (!StringUtils.isEmpty(carDetailRequest.getVin())) {
            carDetailRequest.setVin(carDetailRequest.getVin().toUpperCase());
        }
        if (!StringUtils.isEmpty(carDetailRequest.getLicencePlateNumber())) {
            carDetailRequest.setLicencePlateNumber(carDetailRequest.getLicencePlateNumber().toUpperCase());
        }
    }
}
//TODO add condition if (carDetailRequest.getVin() == null || VinChecker.validate(carDetailRequest.getVin()))
