package ru.alexsumin.notes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexsumin.notes.model.Note;
import ru.alexsumin.notes.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class NoteDAO {

    private final EntityManager em;


    //TODO: find solution for set relations between User and his Notes
    private User user;


    @Autowired
    public NoteDAO(EntityManager em) {
        this.em = em;
        user = em.createNamedQuery(User.FindById, User.class).setParameter("id", 1).getSingleResult();
    }


    @Transactional
    public int createNote(Note note) {
        em.getTransaction().begin();
        note.setLastEdit(new Date());
        em.persist(note);
        em.getTransaction().commit();
        int id = note.getNoteId();

        return id;

    }


    public int update(Note note) {
        em.getTransaction().begin();
//        Note forUpdate = em.find(Note.class, note.getNoteId());
//        forUpdate.setCaption(note.getCaption());
//        forUpdate.setText(note.getText());
//        forUpdate.setLastEdit(note.getLastEdit());
//
//        em.persist(forUpdate);
//        em.getTransaction().commit();
//
//        return forUpdate.getNoteId();
        note.setLastEdit(new Date());
        em.merge(note);



        em.getTransaction().commit();


        return note.getNoteId();
    }

    public int delete(int noteId) {
        em.getTransaction().begin();
        Note note = em.createNamedQuery((Note.FindById), Note.class)
                .setParameter("id", noteId).getSingleResult();
        int id = note.getNoteId();
        em.remove(note);
        em.getTransaction().commit();

        return id;
    }


    public Note get(int noteId) {
        Note note = em.find(Note.class, noteId);
        return note;
    }

    public List<Note> list() {
        //TODO: только для определнного пользователя
        List<Note> list = em.createQuery("from Note").getResultList();
        return list;
    }
}
