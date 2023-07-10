package ru.spring.boot_security.service;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.boot_security.dao.UserDao;
import ru.spring.boot_security.model.Role;
import ru.spring.boot_security.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImp(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Transactional
    @Override
    public void update(User user, Set<String> roleNameSet) {
        Set<Role> roleSet = new HashSet<>();
        System.out.println(roleNameSet);
        user.setPass(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleSet(roleSet);
        userDao.update(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        try {
            userDao.delete(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Введены неверные данные", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersList() {
        return userDao.getUsersList();
    }

    @Transactional()
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        Hibernate.initialize(user.getRoleSet());
        return user;
    }

    @Transactional
    @Override
    public User UserNameCheck(User user, Set<String> roleNameSet) {
        if (userDao.getByName(user.getName()) != null) {
            throw new IllegalArgumentException("Пользователь с таким именем существует");
        }
        Set<Role> roleSet = new HashSet<>();
        System.out.println(roleNameSet);
        user.setPass(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleSet(roleSet);
        return user;
    }

    @Transactional
    @Override
    public User UserNameCheck(User user) {
        if (userDao.getByName(user.getName()) != null) {
            throw new IllegalArgumentException("Пользователь с таким именем существует");
        }
        return user;
    }

    @Transactional
    @Override
    public User UserIdCheck(User user) {
        if (userDao.getById(user.getId()) == null) {
            throw new IllegalArgumentException(String.format("Пользователь с id %d не найден", user.getId()));
        }
        return user;
    }

}