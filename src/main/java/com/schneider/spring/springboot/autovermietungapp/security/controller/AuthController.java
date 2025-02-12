package com.schneider.spring.springboot.autovermietungapp.security.controller;


import com.schneider.spring.springboot.autovermietungapp.security.dto.JwtRequestDto;
import com.schneider.spring.springboot.autovermietungapp.security.dto.JwtResponseDto;
import com.schneider.spring.springboot.autovermietungapp.security.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    /**
     * The authentication service bean for handling authentication operations.
     */

    private AuthService authService;

    /**
     * Handles user login and returns a JWT response.
     *
     * @param authRequest the authentication request containing username and password.
     * @return a ResponseEntity containing the JWT response.
     * @throws AuthException if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto authRequest) throws AuthException {
        final JwtResponseDto token = authService.login(authRequest);
        return ResponseEntity.ok(token);

//        @PostMapping("/registration")
//        @ResponseStatus(code = HttpStatus.CREATED)
//        public ResponseEntity<UserDto> register (@RequestBody UserDto userCredentialsDto){
//            UserDto userDto = authService.insertUsers(userCredentialsDto);
//            return ResponseEntity.ofNullable(userDto);
//        }
    }
}

