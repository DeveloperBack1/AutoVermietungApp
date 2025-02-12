package com.schneider.spring.springboot.autovermietungapp.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {
    /**
     * The username (login) of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;
}
