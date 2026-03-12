package com.example.demo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecurityConfiguration {

    @Value("${security.auth.type}")
    private String authType;

    @Value("${security.token.expiration-in-seconds}")
    private Long tokenExpirationTimeInSeconds;

}
