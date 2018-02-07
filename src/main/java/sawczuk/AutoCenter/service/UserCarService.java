package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.UserCar;

import java.util.List;

public interface UserCarService {
    void save(UserCar userCar);

    void delete(Long id);

    UserCar findOne(Long id);

    List<UserCar> findAllByUserId(Long userId);
}
