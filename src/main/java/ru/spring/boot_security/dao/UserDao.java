package ru.spring.boot_security.dao;


import ru.spring.boot_security.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User getByName(String username);

    User getById(Long id);

    void update(User user);

    void delete(long id);

    List<User> getUsersList();
}
