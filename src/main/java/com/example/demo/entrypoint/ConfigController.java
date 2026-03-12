package com.example.demo.entrypoint;

import com.example.demo.config.SecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    private final SecurityConfiguration securityConfiguration;

    @GetMapping
    public void getConfig(){
        final var authType = securityConfiguration.getAuthType();
        final var tokenTime = securityConfiguration.getTokenExpirationTimeInSeconds();
    }

}
