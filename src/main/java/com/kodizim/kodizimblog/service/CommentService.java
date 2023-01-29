package com.kodizim.kodizimblog.service;

import com.kodizim.kodizimblog.entity.Comment;
import com.kodizim.kodizimblog.repository.BlogRepository;
import com.kodizim.kodizimblog.repository.CommentRepository;
import com.kodizim.kodizimblog.repository.UserRepository;
import com.kodizim.kodizimblog.valueobjects.AddCommentCommand;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;


    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public void addComment(AddCommentCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow();
        var blog = blogRepository.findById(command.blogId())
                .orElseThrow();
        var comment = new Comment(
                UUID.randomUUID(),
                command.content(),
                OffsetDateTime.now(),
                user,
                blog
        );
        commentRepository.save(comment);
    }
}
