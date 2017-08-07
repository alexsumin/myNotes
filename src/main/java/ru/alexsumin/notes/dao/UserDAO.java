package ru.alexsumin.notes.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alexsumin.notes.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserDAO {

    private final EntityManager em;
    private final PasswordEncoder encoder;


    @Autowired
    public UserDAO(EntityManager em, PasswordEncoder encoder) {
        this.em = em;
        this.encoder = encoder;
    }

    public int add(User user) {
        em.getTransaction().begin();
        user.setEncryptedPassword(encoder.encode(user.getEncryptedPassword()));
        em.persist(user);
        em.getTransaction().commit();
        int id = user.getUserId();

        return id;
    }

    public int update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

        return user.getUserId();
    }


    public int delete(int userId) {
        em.getTransaction().begin();
        User user = em.createNamedQuery((User.FindById), User.class).setParameter("id", userId).getSingleResult();
        int id = user.getUserId();
        em.remove(user);
        em.getTransaction().commit();

        return id;
    }

    public User get(int userId) {
        User user = em.find(User.class, userId);

        return user;
    }

    public User findByLogin(String login) {
        User user = em.createNamedQuery((User.FindByLogin), User.class).setParameter("login", login).getSingleResult();

        return user;
    }

    public List<User> list() {
        List<User> list = em.createQuery("from User").getResultList();
        return list;

    }

}
