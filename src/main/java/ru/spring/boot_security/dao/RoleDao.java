package ru.spring.boot_security.dao;

import ru.spring.boot_security.model.Role;

public interface RoleDao {
    void add(Role role);

    Role findByName(String roleName);
}
