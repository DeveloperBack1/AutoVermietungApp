package com.schneider.spring.springboot.autovermietungapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Login request.
 * <p>
 * This class is used for transferring user credentials (email and password) during authentication.
 */
@Getter
@Setter
@Schema(description = "Login request containing user credentials for authentication")
public class LoginRequest {

    @Schema(description = "The email of the user", example = "user@example.com", required = true)
    private String email;

    @Schema(description = "The password of the user", example = "password123", required = true)
    private String password;
}
