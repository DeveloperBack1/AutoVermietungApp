package com.schneider.spring.springboot.autovermietungapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Login request.
 * <p>
 * This class is used for transferring user credentials (email and password) during authentication.
 */
@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}