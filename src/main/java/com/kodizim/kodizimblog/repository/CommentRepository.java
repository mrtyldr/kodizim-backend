package com.kodizim.kodizimblog.repository;

import com.kodizim.kodizimblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
