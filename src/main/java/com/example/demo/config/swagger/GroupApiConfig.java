package com.example.demo.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .displayName("API Pública")
                .pathsToMatch("/products/v1/**")
                .pathsToExclude("/api/v1/admin/**", "/api/v1/internal/**")
                .build();
    }

    @Bean
    public GroupedOpenApi internalApi() {
        return GroupedOpenApi.builder()
                .group("internal")
                .displayName("API Interna")
                .pathsToMatch("/internal/**")
                .build();
    }
}


