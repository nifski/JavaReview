package com.PharmVault.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DebugController {

    @Value("${DATABASE_URL:NOT_FOUND}")
    private String databaseUrl;

    @Value("${DATABASE_USERNAME:NOT_FOUND}")
    private String databaseUsername;

    @Value("${JWT_SECRET_KEY:NOT_FOUND}")
    private String jwtSecret;

    @Value("${server.port:NOT_FOUND}")
    private String serverPort;

    @GetMapping("/debug-env")
    public ResponseEntity<Map<String, String>> getEnvironmentVariables() {
        Map<String, String> envVars = new HashMap<>();
        envVars.put("DATABASE_URL", databaseUrl);
        envVars.put("DATABASE_USERNAME", databaseUsername);
        envVars.put("JWT_SECRET_KEY", jwtSecret);
        envVars.put("SERVER_PORT", serverPort);

        // This will print the values to your Render logs
        System.out.println("DEBUG ENV VARS: " + envVars);

        return ResponseEntity.ok(envVars);
    }
}
