package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.UserCar;

import java.util.List;

public interface UserCarService {
    void save(UserCar userCar);

    List<UserCar> findAllByUserId(Long userId);
}
