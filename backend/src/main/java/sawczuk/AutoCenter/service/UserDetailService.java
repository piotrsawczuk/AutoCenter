package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.UserDetail;

import java.util.Optional;

public interface UserDetailService {
    void save(UserDetail userDetail);

    Optional<UserDetail> findByUserId(Long userId);

    Optional<UserDetail> findByUserUsernameIgnoreCase(String username);
}
