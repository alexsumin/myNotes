package ru.alexsumin.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexsumin.notes.dao.NoteDAO;
import ru.alexsumin.notes.model.Note;

import java.util.List;


@Service
public class NoteService {

    @Autowired
    NoteDAO dao;

    public int add(Note note) {
        return dao.createNote(note);
    }

    public int update(Note note) {
        return dao.update(note);
    }

    public int delete(int id) {
        return dao.delete(id);
    }

    public Note get(int id) {
        return dao.get(id);
    }

    public List<Note> list() {
        return dao.list();
    }
}
