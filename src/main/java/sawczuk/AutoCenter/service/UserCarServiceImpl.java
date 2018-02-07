package sawczuk.AutoCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.UserCar;
import sawczuk.AutoCenter.repository.UserCarRepository;

import java.util.List;

@Service
public class UserCarServiceImpl implements UserCarService{

    private UserCarRepository userCarRepository;
    private UserService userService;

    @Autowired
    public UserCarServiceImpl(UserCarRepository userCarRepository, UserService userService) {
        this.userCarRepository = userCarRepository;
        this.userService = userService;
    }

    @Override
    public void save(UserCar userCar) {
        userCarRepository.save(userCar);
    }

    @Override
    public List<UserCar> findAllByUserId(Long userId) {
        return userCarRepository.findAllByUserId(userId);
    }
}
