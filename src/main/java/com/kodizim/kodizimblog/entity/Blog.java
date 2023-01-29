package com.kodizim.kodizimblog.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Blog {
    @Id
    private UUID id;

    private String title;

    private String content;

    private OffsetDateTime createdOn;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Blog(UUID id, String title, String content, OffsetDateTime createdOn, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.comments = comments;
    }

    public Blog(UUID id, String title, String content, OffsetDateTime createdOn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
    }

    public Blog() {
    }

    private Blog(Builder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
        createdOn = builder.createdOn;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id.equals(blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class Builder {
        private UUID id;
        private String title;
        private String content;
        private OffsetDateTime createdOn;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder createdOn(OffsetDateTime val) {
            createdOn = val;
            return this;
        }

        public Blog build() {
            return new Blog(this);
        }
    }
}
