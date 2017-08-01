package ru.alexsumin.notes;

import ru.alexsumin.notes.model.Note;

import java.util.List;

/**
 * Created by alex on 01.08.17.
 */
public class NoteBean {
    private List<Note> list;


    public List<Note> getList() {
        return list;
    }

    public void setList(List<Note> list) {
        this.list = list;
    }
}
