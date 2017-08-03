package ru.alexsumin.notes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.alexsumin.notes.dao.NoteDAO;
import ru.alexsumin.notes.model.Note;
import ru.alexsumin.notes.model.User;
import ru.alexsumin.notes.util.ProductionConfiguration;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alex on 31.07.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProductionConfiguration.class)
public class NoteDAOTest {


    @Autowired
    private EntityManager em;

    @Autowired
    private NoteDAO dao;

    @Test
    public void createUpdateTest() {

        User user = new User();
        user.setEmail("test@mail222");
        user.setPassword("passtest222");
        em.persist(user);


        Note note = new Note();
        note.setCaption("other note");
        note.setText("well, this is note. do you see it?");
        note.setUser(user);
        note.setLastEdit(new Date());

        int id = dao.createNote(note);


        Note editNote = dao.get(id);
        String s = "wow, wow, my friend, i hope you see text after modification";
        editNote.setText(s);


        int secondId = dao.update(editNote);

        assertEquals(id, secondId);
        assertEquals(s, dao.get(id).getText());
    }

    @Test
    public void createRusNote() {
        Note note = new Note();
        note.setCaption("кириллица");
        note.setText("это текст. на кириллице. не правда ли?");
        note.setLastEdit(new Date());

        dao.createNote(note);
    }


    @Test
    public void getTest() {
        int id = dao.get(2).getNoteId();

        assertEquals(id, dao.get(2).getNoteId());
        System.out.println(dao.get(2).getText());

        assertNotEquals(id, dao.get(3).getNoteId());
        System.out.println(dao.get(3).getText());
    }

    @Test
    public void listTest() {
        List<Note> list = dao.list();
        for (Note n : list) {
            System.out.println(n);
        }
    }

    @Test
    public void deleteTest() {
        dao.delete(3);
        assertNull(dao.get(3));
    }
}
