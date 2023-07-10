package ru.spring.boot_security.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.spring.boot_security.model.Role;
import ru.spring.boot_security.model.User;
import ru.spring.boot_security.service.RoleService;
import ru.spring.boot_security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInit {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DataInit(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostConstruct
    protected void CreateAdmin() {
        Role.setRole("ROLE_ADMIN", "ADMIN");
        Role.setRole("ROLE_USER", "USER");
        roleService.add(Role.getRole("ROLE_ADMIN"));
        roleService.add(Role.getRole("ROLE_USER"));
        userService.add(new User("Admin", bCryptPasswordEncoder.encode("12345"), Set.of(Role.getRole("ROLE_ADMIN"), Role.getRole("ROLE_USER"))));
    }
}