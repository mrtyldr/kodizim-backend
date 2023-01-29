package com.kodizim.kodizimblog.controller;

import com.kodizim.kodizimblog.BaseIntegrationTest;
import com.kodizim.kodizimblog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BlogControllerTest extends BaseIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    BlogRepository blogrepository;
    @Test
    void should_add_blog() throws Exception {
        var request = post("/api/blog")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "title" : "First Commit",
                        "content" : "Osmanın biraz terbiyesi olduğunu düşünüyorum :)"
                        }
                        """);

        mockMvc.perform(request)
                .andExpect(status()
                        .isNoContent());

        assertThat(blogrepository.count()).isEqualTo(1);
    }
    @Test
    void should_get_blogDtos() throws Exception{
        var request = get("/api/blog");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                {
                                "title":"First Commit",
                                "content":"Osmanın biraz terbiyesi olduğunu düşünüyorum :)",
                                "createdOn":"2023-01-23T13:40:44.379383+03:00"
                                }
                                ]
                                """
                ));
    }

}