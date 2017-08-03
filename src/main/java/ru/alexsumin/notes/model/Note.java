package ru.alexsumin.notes.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
@NamedQueries({
        @NamedQuery(
                name = Note.FindById,
                query = "select n from Note n where n.noteId = :id"
        )})
public class Note {

    public static final String FindById = "Note.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", updatable = false, insertable = false)
    private int noteId;

    @Column
    private String caption;

    @Column(columnDefinition = "text")
    private String text;

    @Column(name = "last_edit", nullable = false)
    private Date lastEdit;

    @ManyToOne
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", caption='" + caption + '\'' +
                ", text='" + text + '\'' +
                ", lastEdit=" + lastEdit +
                ", user=" + user +
                '}';
    }
}