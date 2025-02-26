package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.dto.LoginRequest;
import com.schneider.spring.springboot.autovermietungapp.security.jwt.JwtUtils;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for handling authentication related requests.
 * <p>
 * Provides an endpoint for users to log in and obtain a JWT token.
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController  {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Authenticates a user and returns a JWT token upon successful login.
     *
     * @param loginRequest the login credentials (email and password)
     * @return ResponseEntity containing the JWT token
     */

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(
            @Parameter(description = "Login credentials containing email and password") @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(Map.of("token", jwtToken));
    }
}