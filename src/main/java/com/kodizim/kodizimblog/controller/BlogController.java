package com.kodizim.kodizimblog.controller;

import com.kodizim.kodizimblog.dto.BlogDto;
import com.kodizim.kodizimblog.service.BlogService;
import com.kodizim.kodizimblog.valueobjects.AddBlogCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addBlog(@RequestBody AddBlogCommand command) {
        blogService.addBlog(command);
    }

    @GetMapping("")
    List<BlogDto> getBlogs() {
        return blogService.getBlogs();
    }
}
