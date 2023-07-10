package ru.spring.boot_security.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.boot_security.model.Role;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImp implements RoleDao{

    private final EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }
    @Override
    public Role findByName(String roleName) {
        try {
            return entityManager.createQuery("from Role where name=:roleName", Role.class).setParameter("roleName", roleName).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
