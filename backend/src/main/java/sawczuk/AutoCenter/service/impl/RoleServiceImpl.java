package sawczuk.AutoCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.Role;
import sawczuk.AutoCenter.repository.RoleRepository;
import sawczuk.AutoCenter.service.RoleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByNameIgnoreCase(String name) {
        return roleRepository.findByNameIgnoreCase(name);
    }
}
