package com.kodizim.kodizimblog.controller;

import com.kodizim.kodizimblog.BaseIntegrationTest;
import com.kodizim.kodizimblog.entity.User;
import com.kodizim.kodizimblog.repository.CommentRepository;
import com.kodizim.kodizimblog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest extends BaseIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    void should_add_comment_on_blog() throws Exception {
        var user = userRepository.save(new User(
                UUID.randomUUID(),
                "osman@kodizim.com",
                "osman osmanoğlu"
        ));

        var request = MockMvcRequestBuilders.post("/api/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "blogId": "38105e7a-a3d7-4c88-819e-08ee9af58394",
                        "userId" : "%s",
                        "content" : "Osman osmanoğlu osmanlı yıkılmadan önce doğan son osmanlı çocuğu."
                        }
                        """.formatted(user.getId()));
        mockMvc.perform(request)
                .andExpect(status().isNoContent());

        assertThat(commentRepository.count()).isEqualTo(1);
    }
}