package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.exception.PasswordException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;
import sawczuk.AutoCenter.model.User;
import sawczuk.AutoCenter.model.dto.UserRequest;
import sawczuk.AutoCenter.model.dto.UserResponse;
import sawczuk.AutoCenter.repository.RoleRepository;
import sawczuk.AutoCenter.repository.UserRepository;
import sawczuk.AutoCenter.security.LoggedInUserProvider;
import sawczuk.AutoCenter.service.UserService;
import sawczuk.AutoCenter.service.mapper.DtoEntityMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_ROLE_NAME = "user";
    private static final String ADMIN_ROLE_NAME = "admin";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(UserRequest userRequest) throws ResourceNotFoundException {
        return save(userRequest, false);
    }

    @Override
    public UserResponse save(UserRequest userRequest, boolean isRoleAdmin) throws ResourceNotFoundException {
        User user = new User();
        DtoEntityMapper.map(userRequest, user);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);
        userRoleSetter(user, isRoleAdmin);
        return DtoEntityMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public UserResponse update(UserRequest userRequest) throws PasswordException, ResourceNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(LoggedInUserProvider.findLoggedInUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", LoggedInUserProvider.findLoggedInUsername()));
        if (!StringUtils.isEmpty(userRequest.getUsername())) {
            user.setUsername(userRequest.getUsername());
        }
        if (!StringUtils.isEmpty(userRequest.getEmail())) {
            user.setEmail(userRequest.getEmail());
        }
        if (!StringUtils.isEmpty(userRequest.getPassword())) {
            passwordValidator(userRequest, user);
        }

        return DtoEntityMapper.map(userRepository.save(user), UserResponse.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(Long id) throws ResourceNotFoundException {
        return DtoEntityMapper.map(
                userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)),
                UserResponse.class);
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        return DtoEntityMapper.mapAll(userRepository.findAll(pageable), UserResponse.class);
    }

    private void userRoleSetter(User user, boolean isRoleAdmin) throws ResourceNotFoundException {
        if (isRoleAdmin) {
            user.setRoles(new HashSet<>(Arrays.asList(
                    roleRepository.findByNameIgnoreCase(USER_ROLE_NAME)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", USER_ROLE_NAME)),
                    roleRepository.findByNameIgnoreCase(ADMIN_ROLE_NAME)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", ADMIN_ROLE_NAME))
            )));
        } else {
            user.setRoles(new HashSet<>(Collections.singletonList(
                    roleRepository.findByNameIgnoreCase(USER_ROLE_NAME)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", USER_ROLE_NAME)))));
        }
    }

    private void passwordValidator(UserRequest userRequest, User user) throws PasswordException {
        if (StringUtils.isEmpty(userRequest.getCurrentPassword())) {
            throw new PasswordException("Current password not provided.");
        }
        if (!passwordEncoder.matches(userRequest.getCurrentPassword(), user.getPassword())) {
            throw new PasswordException("Current password provided is incorrect.");
        }
        if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new PasswordException("New password is same as current password.");
        }

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    }
}