package com.schneider.spring.springboot.autovermietungapp.controller;

import com.schneider.spring.springboot.autovermietungapp.dto.LoginRequest;
import com.schneider.spring.springboot.autovermietungapp.security.jwt.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * AuthController handles authentication requests for the AutoVermietungApp.
 * <p>
 * Provides an endpoint for users to authenticate and receive a JWT token.
 * </p>
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and JWT generation")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    /**
     * Authenticates a user and generates a JWT token upon successful login.
     *
     * @param loginRequest the user's login credentials (email and password)
     * @return a JWT token if authentication is successful
     */
    @Operation(
            summary = "User Login",
            description = "Authenticates a user using their email and password, returning a JWT token upon successful authentication."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\"}"))),
            @ApiResponse(responseCode = "401", description = "Invalid email or password", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Generate JWT token
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        // Return token as JSON response
        return ResponseEntity.ok(Map.of("token", jwtToken));
    }
}

