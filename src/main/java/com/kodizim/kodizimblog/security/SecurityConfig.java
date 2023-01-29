package com.kodizim.kodizimblog.security;

import com.kodizim.kodizimblog.infrastructure.auth0.AudienceValidator;
import com.kodizim.kodizimblog.infrastructure.auth0.Auth0ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final Auth0ConfigurationProperties properties;
    public SecurityConfig(Auth0ConfigurationProperties properties) {
        this.properties = properties;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors()
                .and()
                .csrf()
                .disable();

        http.oauth2ResourceServer(config -> config.jwt().decoder(jwtDecoder())
                .jwtAuthenticationConverter(new JwtConverter("USER")));

        http.authorizeHttpRequests()
                .requestMatchers("/swagger-ui.html")
                .permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {

        NimbusJwtDecoder jwtDecoder =
                JwtDecoders.fromOidcIssuerLocation(properties.getIssuerUri());

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(properties.getAudience());
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(properties.getIssuerUri());
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }
}
