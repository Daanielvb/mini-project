package com.example.demo.entrypoint;

import com.example.demo.config.SecurityConfiguration;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/v1/")
@Tag(name="Internal config controller", description="Internals only")
@RequiredArgsConstructor
public class ConfigController {

    private final SecurityConfiguration securityConfiguration;

    @GetMapping("/config")
    public ResponseEntity<Void> getConfig(){
        final var authType = securityConfiguration.getAuthType();
        final var tokenTime = securityConfiguration.getTokenExpirationTimeInSeconds();
        return ResponseEntity.noContent().build();
    }

}
