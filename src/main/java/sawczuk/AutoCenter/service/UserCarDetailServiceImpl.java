package sawczuk.AutoCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.UserCarDetail;
import sawczuk.AutoCenter.repository.UserCarDetailRepository;

@Service
public class UserCarDetailServiceImpl implements UserCarDetailService {

    UserCarDetailRepository userCarDetailRepository;

    @Autowired
    public UserCarDetailServiceImpl(UserCarDetailRepository userCarDetailRepository) {
        this.userCarDetailRepository = userCarDetailRepository;
    }

    @Override
    public void save(UserCarDetail userCarDetail) {
        userCarDetailRepository.save(userCarDetail);
    }

    @Override
    public UserCarDetail findOneByUserCarId(Long carId) {
        return userCarDetailRepository.findOneByUserCarId(carId);
    }

}
