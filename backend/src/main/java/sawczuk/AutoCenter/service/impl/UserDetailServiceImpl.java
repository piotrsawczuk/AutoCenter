package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.UserDetail;
import sawczuk.AutoCenter.model.dto.UserDetailRequest;
import sawczuk.AutoCenter.model.dto.UserDetailResponse;
import sawczuk.AutoCenter.repository.UserDetailRepository;
import sawczuk.AutoCenter.repository.UserRepository;
import sawczuk.AutoCenter.security.LoggedInUserProvider;
import sawczuk.AutoCenter.service.UserDetailService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetailResponse save(UserDetailRequest userDetailRequest) throws ResourceNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", LoggedInUserProvider.findLoggedInUsername()));
        UserDetail userDetail = new UserDetail();
        DtoEntityMapper.mapWithNulls(userDetailRequest, userDetail);
        userDetail.setUser(user);
        userDetailRepository.save(userDetail);
        return DtoEntityMapper.map(userDetail, UserDetailResponse.class);
    }

    @Override
    public UserDetailResponse update(UserDetailRequest userDetailRequest) throws ResourceNotFoundException {
        UserDetail userDetail = userDetailRepository.findByUserUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User detail", "username", LoggedInUserProvider.findLoggedInUsername()));
        DtoEntityMapper.mapWithNulls(userDetailRequest, userDetail);
        userDetailRepository.save(userDetail);
        return DtoEntityMapper.map(userDetail, UserDetailResponse.class);
    }

    @Override
    public UserDetailResponse find() throws ResourceNotFoundException {
        Long userId = userRepository.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User ID", "username", LoggedInUserProvider.findLoggedInUsername()))
                .getId();
        return userDetailRepository.findByUserId(userId)
                .map(userDetail -> DtoEntityMapper.map(userDetail, UserDetailResponse.class))
                .orElse(null);
    }
}
