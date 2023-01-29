package com.kodizim.kodizimblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class ClockConfig {

    @Bean
    Clock getClock(){
        return Clock.fixed(Instant.parse("2023-01-23T13:40:44.379383+03:00"), ZoneId.of("UTC"));
    }
}
