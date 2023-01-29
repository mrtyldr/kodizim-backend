package com.kodizim.kodizimblog.infrastructure.auth0;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("auth0")
@Configuration
public class Auth0ConfigurationProperties {
    private String audience;
    private String issuerUri;

    public Auth0ConfigurationProperties() {
    }

    public Auth0ConfigurationProperties(
            String audience,
            String issuerUri
    ) {
        this.audience = audience;
        this.issuerUri = issuerUri;
    }


    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getIssuerUri() {
        return issuerUri;
    }

    public void setIssuerUri(String issuerUri) {
        this.issuerUri = issuerUri;
    }
}
