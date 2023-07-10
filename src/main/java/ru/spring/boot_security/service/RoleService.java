package ru.spring.boot_security.service;

import ru.spring.boot_security.model.Role;

public interface RoleService {
    void add(Role role);

    Role findByName(String roleName);

}
