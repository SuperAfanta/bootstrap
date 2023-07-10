package ru.spring.boot_security.dao;


import org.springframework.stereotype.Repository;
import ru.spring.boot_security.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getByName(String username) {
            try {
                return entityManager.createQuery("from User where name=:user", User.class).setParameter("user", username).getSingleResult();
            } catch (Exception e) {
                return null;
            }
    }

    @Override
    public User getById(Long id){
        System.out.println(entityManager.find(User.class, id));
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getUsersList() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}