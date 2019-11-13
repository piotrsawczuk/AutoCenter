package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.UserDetail;

public interface UserDetailService {
    void save(UserDetail userDetail);

    UserDetail findByUserId(Long userId);

    UserDetail findByUserUsernameIgnoreCase(String username);
}
