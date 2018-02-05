package sawczuk.AutoCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.model.Role;
import sawczuk.AutoCenter.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByNameIgnoreCase(String name) {
        return roleRepository.findByNameIgnoreCase(name);
    }
}
