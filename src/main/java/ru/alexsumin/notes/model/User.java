package ru.alexsumin.notes.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alex on 30.07.17.
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = User.FindByEmail,
                query = "select u from User u where u.email = :email"
        ),
        @NamedQuery(name = User.ALL_USERS,
                query = "select u from User u"),
        @NamedQuery(
                name = User.FindById,
                query = "select u from User u where u.userId = :id"
        )

})
@Table(name = "users")
public class User {
    public static final String ALL_USERS = "User.allUsers";
    public static final String FindByEmail = "User.findByEmail";
    public static final String FindById = "User.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Note> notes;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<TaskList> taskLists;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
//    public List<TaskList> getTaskLists() {
//        return taskLists;
//    }
//
//    public void setTaskLists(List<TaskList> taskLists) {
//        this.taskLists = taskLists;
//    }
}