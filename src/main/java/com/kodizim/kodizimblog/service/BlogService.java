package com.kodizim.kodizimblog.service;

import com.kodizim.kodizimblog.dto.BlogDto;
import com.kodizim.kodizimblog.entity.Blog;
import com.kodizim.kodizimblog.repository.BlogRepository;
import com.kodizim.kodizimblog.valueobjects.AddBlogCommand;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    public void addBlog(AddBlogCommand command) {
        var blog = new Blog(
                UUID.randomUUID(),
                command.getTitle(),
                command.getContent(),
                OffsetDateTime.now()
        );
        blogRepository.save(blog);
    }

    public List<BlogDto> getBlogs() {
        return blogRepository.getBlogDtos();
    }
}
