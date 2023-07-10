package ru.spring.boot_security.service;


import org.springframework.transaction.annotation.Transactional;
import ru.spring.boot_security.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {


    void add(User user);

    void update(User user, Set<String> roleNameSet);

    @Transactional
    void update(User user);

    void delete(long id);

    List<User> getUsersList();

    User UserNameCheck(User user, Set<String> roleNameSet);

    User UserNameCheck(User user);

    User UserIdCheck(User user);
}