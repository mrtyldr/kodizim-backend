package com.kodizim.kodizimblog.controller;

import com.kodizim.kodizimblog.service.CommentService;
import com.kodizim.kodizimblog.valueobjects.AddCommentCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addComment(@RequestBody AddCommentCommand command){
        commentService.addComment(command);
    }

}
