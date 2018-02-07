package sawczuk.AutoCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.UserCar;
import sawczuk.AutoCenter.repository.UserCarRepository;

import java.util.List;

@Service
public class UserCarServiceImpl implements UserCarService {

    private UserCarRepository userCarRepository;

    @Autowired
    public UserCarServiceImpl(UserCarRepository userCarRepository) {
        this.userCarRepository = userCarRepository;
    }

    @Override
    public void save(UserCar userCar) {
        userCarRepository.save(userCar);
    }

    @Override
    public void delete(Long id) {
        userCarRepository.delete(id);
    }

    @Override
    public UserCar findOne(Long id) {
        return userCarRepository.findOne(id);
    }

    @Override
    public List<UserCar> findAllByUserId(Long userId) {
        return userCarRepository.findAllByUserId(userId);
    }
}
