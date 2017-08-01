import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alexsumin.notes.model.Note;
import ru.alexsumin.notes.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by alex on 30.07.17.
 */
public class HibernateTest {


    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("PostgrePersistentUnit");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    @Test
    public void testUser() throws Exception {
        em.getTransaction().begin();

        User user = new User();
        user.setEmail("root@root.ru");
        user.setPassword("password");

        em.persist(user);

        em.getTransaction().commit();

        assertEquals("root@root.ru", em.find(User.class, user.getUserId()).getEmail());
    }

    @Test
    public void testNotes() throws Exception {
        em.getTransaction().begin();

        User user = new User();
        user.setEmail("test@user.com");
        user.setPassword("testpass");

        em.persist(user);

        Note note = new Note();
        note.setText("Hello this is test-note! Can you see me?");
        note.setLastEdit(new Date());
        note.setUser(user);

        em.persist(note);


        em.getTransaction().commit();

        em.refresh(user);

        List<Note> notes = user.getNotes();
        assertNotNull(notes);
        assertEquals(1, notes.size());
        assertEquals(note, notes.get(0));
        assertEquals("Hello this is test-note! Can you see me?", notes.get(notes.size() - 1).getText());
    }

    @Test
    public void ShowAllUsers() {
        em.getTransaction().begin();


        for (User u : em.createNamedQuery(User.ALL_USERS, User.class).getResultList()) {
            System.out.println(u.getUserId() + " email = " + u.getEmail());
        }
    }


    @Test
    public void findNoteById() {
        em.getTransaction().begin();

        User user = new User();
        user.setEmail("other1@example.com");
        user.setPassword("examplepass1");


        em.persist(user);


        Note note = new Note();
        note.setUser(user);
        note.setText("some text");
        note.setLastEdit(new Date());

        em.persist(note);
        em.getTransaction().commit();

        Note otherNote = em.find(Note.class, note.getNoteId());
        System.out.println(otherNote.getText());
        assertEquals("some text", otherNote.getText());

    }


    @Test
    public void updateTest() {
        em.getTransaction().begin();


        Note note = em.createNamedQuery(Note.FindById, Note.class)
                .setParameter("id", 1).getSingleResult();
        int temp = note.getNoteId();
        System.out.println(note.getText());

        String s = "some changed text";
        note.setText(s);
        //em.refresh(note);
        em.persist(note);
        em.getTransaction().commit();


        Note updatedNote = em.find(Note.class, temp);
        assertEquals(s, updatedNote.getText());


    }

    @Test
    public void findUserTest() {
        User user = em.createNamedQuery(User.FindById, User.class).setParameter("id", 1).getSingleResult();

        System.out.println(user);
    }


    @Test
    public void testList() {
        List<Note> list = em.createQuery("from Note").getResultList();
        for (Note note : list) {
            System.out.println(note);
        }
    }
}