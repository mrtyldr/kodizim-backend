package com.kodizim.kodizimblog.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class Comment {
    @Id
    private UUID id;


    private String content;
    private OffsetDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment(UUID id,
                   String content,
                   OffsetDateTime createdOn) {
        this.id = id;
        this.content = content;
        this.createdOn = createdOn;
    }

    public Comment(UUID id, String content,
                   OffsetDateTime createdOn,
                   User user, Blog blog) {
        this.id = id;
        this.content = content;
        this.createdOn = createdOn;
        this.user = user;
        this.blog = blog;
    }

    public Comment() {
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public String getContent() {
        return content;
    }
}
