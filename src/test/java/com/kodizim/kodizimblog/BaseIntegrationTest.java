package com.kodizim.kodizimblog;

import com.kodizim.kodizimblog.repository.BlogRepository;
import com.kodizim.kodizimblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local-test")
public class BaseIntegrationTest {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    CommentRepository commentRepository;


    @Configuration
    @ComponentScan(basePackageClasses = BaseIntegrationTest.class)
    static class TestConfig {
        @Bean
        FlywayMigrationStrategy clean() {
            return flyway -> {
                flyway.clean();
                flyway.migrate();
            };
        }
    }
}
