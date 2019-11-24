package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.dto.UserDetailRequest;
import sawczuk.AutoCenter.model.dto.UserDetailResponse;

public interface UserDetailService {
    UserDetailResponse save(UserDetailRequest userDetailRequest) throws ResourceNotFoundException;

    UserDetailResponse update(UserDetailRequest userDetailRequest) throws ResourceNotFoundException;

    UserDetailResponse findByLoggedInUser() throws ResourceNotFoundException;
}
