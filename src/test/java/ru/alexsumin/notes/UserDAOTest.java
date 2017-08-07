package ru.alexsumin.notes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.alexsumin.notes.dao.UserDAO;
import ru.alexsumin.notes.model.User;
import ru.alexsumin.notes.util.ProductionConfiguration;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductionConfiguration.class)
public class UserDAOTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserDAO dao;

    @Test
    public void addUserTest() {
        User user = new User();
        String s = "other@login";
        user.setLogin(s);
        user.setEncryptedPassword("tra-ta-ta");

        int id = dao.add(user);
        assertEquals(dao.get(id).getLogin(), s);

    }


    @Test
    public void updateTest() {
        User user = new User();
        String s = "after@update1111";
        user.setLogin("before1111@udpate");
        user.setEncryptedPassword("passwordische");

        int id = dao.add(user);

        User otherUser = dao.get(id);
        otherUser.setLogin(s);
        dao.update(otherUser);

        assertEquals(dao.get(id).getLogin(), s);
        assertEquals(id, dao.findByLogin(s).getUserId());

    }

    @Test
    public void listTest() {
        List<User> list = dao.list();
        list.stream().forEach((user -> System.out.println(user.getLogin())));
    }


}
