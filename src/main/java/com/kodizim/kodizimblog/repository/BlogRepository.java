package com.kodizim.kodizimblog.repository;

import com.kodizim.kodizimblog.dto.BlogDto;
import com.kodizim.kodizimblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
@Query("""
        select new com.kodizim.kodizimblog.dto.BlogDto(
        b.title,
        b.content,
        b.createdOn
        )
        from Blog b
        """)
    List<BlogDto> getBlogDtos();
}
