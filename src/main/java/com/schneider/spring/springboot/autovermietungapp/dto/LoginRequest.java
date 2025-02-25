package com.schneider.spring.springboot.autovermietungapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for user login credentials.
 */
@Getter
@Setter
@Schema(description = "DTO representing user login credentials")
public class LoginRequest {

    @Schema(description = "User's email address", example = "user@example.com", required = true)
    private String email;

    @Schema(description = "User's password", example = "Password123", required = true)
    private String password;
}
