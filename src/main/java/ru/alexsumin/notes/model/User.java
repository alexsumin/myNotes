package ru.alexsumin.notes.model;

import javax.persistence.*;
import java.util.List;


@Entity
@NamedQueries({
        @NamedQuery(
                name = User.FindByLogin,
                query = "select u from User u where u.login = :login"
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
    public static final String FindByLogin = "User.findByEmail";
    public static final String FindById = "User.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    @Column(unique = true)
    private String login;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String email) {
        this.login = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String password) {
        this.encryptedPassword = password;
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
                ", email='" + login + '\'' +
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