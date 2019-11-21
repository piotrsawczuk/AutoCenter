package sawczuk.AutoCenter.service;

import sawczuk.AutoCenter.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByNameIgnoreCase(String name);
}
