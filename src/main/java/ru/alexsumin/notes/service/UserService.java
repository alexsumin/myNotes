package ru.alexsumin.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alexsumin.notes.dao.UserDAO;
import ru.alexsumin.notes.model.User;

public class UserService {

    @Autowired
    UserDAO dao;

    public int add(User user) {
        return dao.add(user);
    }

    public int update(User user) {
        return dao.update(user);
    }

    public int delete(int userId) {
        return dao.delete(userId);
    }

    public User get(int userId) {
        return dao.get(userId);
    }

    public User findByLogin(String login) {
        return dao.findByLogin(login);
    }
}
