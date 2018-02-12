package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.UserCarDetail;

public interface UserCarDetailService {
    void save(UserCarDetail userCarDetail);

    UserCarDetail findOneByUserCarId(Long carId);
}
