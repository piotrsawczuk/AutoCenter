package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.repository.UserDetailRepository;
import sawczuk.AutoCenter.service.UserDetailService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository userDetailRepository;

    @Override
    public void save(UserDetail userDetail) {
        userDetailRepository.save(userDetail);
    }

    @Override
    public Optional<UserDetail> findByUserId(Long userId) {
        return userDetailRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserDetail> findByUserUsernameIgnoreCase(String username) {
        return userDetailRepository.findByUserUsernameIgnoreCase(username);
    }
}
