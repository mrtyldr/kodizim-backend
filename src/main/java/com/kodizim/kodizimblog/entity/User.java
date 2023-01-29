package com.kodizim.kodizimblog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User(UUID id, String email, String userName) {
        this.id = id;
        this.email = email;
        this.userName = userName;
    }

    public User() {
    }

    private String userName;


    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public List<Comment> getComments() {
        return comments;
    }

}
