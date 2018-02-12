package sawczuk.AutoCenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.UserCarDetail;
import sawczuk.AutoCenter.repository.UserCarDetailRepository;
import sawczuk.AutoCenter.service.UserCarDetailService;

@Service
public class UserCarDetailServiceImpl implements UserCarDetailService {

    private UserCarDetailRepository userCarDetailRepository;

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
