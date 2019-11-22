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
import sawczuk.AutoCenter.repository.UserRepository;
import sawczuk.AutoCenter.service.RoleService;
import sawczuk.AutoCenter.service.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_ROLE_NAME = "user";
    private static final String ADMIN_ROLE_NAME = "admin";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(User user) throws ResourceNotFoundException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.isRoleAdmin()) {
            user.setRoles(new HashSet<>(Arrays.asList(
                    roleService.findByNameIgnoreCase(USER_ROLE_NAME)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", USER_ROLE_NAME)),
                    roleService.findByNameIgnoreCase(ADMIN_ROLE_NAME)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", ADMIN_ROLE_NAME))
            )));
        } else {
            user.setRoles(new HashSet<>(Collections.singletonList(
                    roleService.findByNameIgnoreCase(USER_ROLE_NAME)
                            .orElseThrow(() -> new ResourceNotFoundException("Role", "name", USER_ROLE_NAME)))));
        }
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void update(UserRequest userRequest, User user) throws PasswordException {
        if (!StringUtils.isEmpty(userRequest.getUsername())) {
            user.setUsername(userRequest.getUsername());
        }
        if (!StringUtils.isEmpty(userRequest.getEmail())) {
            user.setEmail(userRequest.getEmail());
        }
        if (!StringUtils.isEmpty(userRequest.getPassword())) {
            if (!StringUtils.isEmpty(userRequest.getCurrentPassword())) {
                if (passwordEncoder.matches(userRequest.getCurrentPassword(), user.getPassword())) {
                    if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                    } else {
                        throw new PasswordException("New password is same as current password.");
                    }
                } else {
                    throw new PasswordException("Current password provided is incorrect.");
                }
            } else {
                throw new PasswordException("Current password not provided.");
            }
        }
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsernameIgnoreCase(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}