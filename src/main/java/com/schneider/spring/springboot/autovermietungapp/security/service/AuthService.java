package com.schneider.spring.springboot.autovermietungapp.security.service;
import com.schneider.spring.springboot.autovermietungapp.dto.UserDTO;
import com.schneider.spring.springboot.autovermietungapp.security.dto.JwtRequestDto;
import com.schneider.spring.springboot.autovermietungapp.security.dto.JwtResponseDto;
import com.schneider.spring.springboot.autovermietungapp.security.jwt.JwtAuthentication;
import com.schneider.spring.springboot.autovermietungapp.security.jwt.JwtProvider;
import com.schneider.spring.springboot.autovermietungapp.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for handling authentication-related operations.
 * <p>
 * This service provides methods for user login, access token generation, refresh token generation,
 * and obtaining authentication information from the security context.
 * </p>
 *
 * @Service               - Indicates that an annotated class is a service component.
 * @RequiredArgsConstructor - Lombok annotation to generate a constructor for all final fields,
 *                           with parameter order same as field order.
 *
 * @author A-R
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    /**
     * The user service for fetching user data.
     */
    private final UserService userService;

    /**
     * A map for storing refresh tokens against user logins.
     */
    private final Map<String, String> refreshStorage = new HashMap<>();

    /**
     * The JWT provider for generating and validating JWT tokens.
     */
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    /**
     * Handles user login and returns JWT tokens upon successful authentication.
     *
     * @param authRequest the authentication request containing user credentials.
     * @return a JwtResponse containing the generated access and refresh tokens.
     * @throws AuthException if the user is not found or the password is incorrect.
     */
    public JwtResponseDto login(JwtRequestDto authRequest) throws AuthException {
        final UserDTO userDto = userService.getByEmail(authRequest.getEmail());
        if (userDto == null) new AuthException("User is not found");

        if (passwordEncoder.matches(authRequest.getPassword(), userDto.getEmail())) {
            final String accessToken = jwtProvider.generateAccessToken(userDto);
            final String refreshToken = jwtProvider.generateRefreshToken(userDto);
            refreshStorage.put(userDto.getEmail(), refreshToken); // нужно хранить в БД????
            return new JwtResponseDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    /**
     * Generates a new access token using a valid refresh token.
     * <p>
     * This method first validates the provided refresh token using the {@link JwtProvider}.
     * If the token is valid, it extracts the user login from the token claims,
     * retrieves the stored refresh token for the user, and compares it with the provided token.
     * If they match, it fetches the user data, generates a new access token,
     * and returns a {@link JwtResponseDto} with the new access token.
     * If any of the validation steps fail, it returns a {@link JwtResponseDto} with null values.
     * </p>
     *
     * @param refreshToken the refresh token.
     * @return a JwtResponse containing the generated access token, or null values if validation fails.
     * @throws AuthException if the user is not found.
     */
    public JwtResponseDto getAccessToken(@NonNull String refreshToken) throws AuthException {
        // Validate the provided refresh token
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            // Extract claims from the refresh token
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            // Get the user login from the token claims
            final String login = claims.getSubject();
            // Retrieve the stored refresh token for the user
            final String savedRefreshToken = refreshStorage.get(login);
            // Compare the stored refresh token with the provided token
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                // Fetch the user data
                final UserDTO userDto = userService.getByEmail(login);
                if (userDto == null) new AuthException("User is not found");
                // Generate a new access token
                final String accessToken = jwtProvider.generateAccessToken(userDto);
                // Return a JwtResponse with the new access token
                return new JwtResponseDto(accessToken, null);
            }
        }
        // Return a JwtResponse with null values if validation fails
        return new JwtResponseDto(null, null);
    }


    /**
     * Refreshes both access and refresh tokens using a valid refresh token.
     * <p>
     * This method first validates the provided refresh token using the {@link JwtProvider}.
     * If the token is valid, it extracts the user login from the token claims,
     * retrieves the stored refresh token for the user, and compares it with the provided token.
     * If they match, it fetches the user data, generates new access and refresh tokens,
     * updates the stored refresh token for the user, and returns a {@link JwtResponseDto} with the new tokens.
     * If any of the validation steps fail, it throws an {@link AuthException} with a message indicating
     * an invalid JWT token.
     * </p>
     *
     * @param refreshToken the refresh token.
     * @return a JwtResponse containing the generated access and refresh tokens.
     * @throws AuthException if the refresh token is invalid or the user is not found.
     */
    public JwtResponseDto refresh(@NonNull String refreshToken) throws AuthException {
        // Validate the provided refresh token
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            // Extract claims from the refresh token
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            // Get the user login from the token claims
            final String login = claims.getSubject();
            // Retrieve the stored refresh token for the user
            final String savedRefreshToken = refreshStorage.get(login); //переделать из БД
            // Compare the stored refresh token with the provided token
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                // Fetch the user data
                final UserDTO userDto = userService.getByEmail(login);
                if (userDto == null) new AuthException("User is not found");
                // Generate new access and refresh tokens
                final String newAccessToken = jwtProvider.generateAccessToken(userDto);
                final String newRefreshToken = jwtProvider.generateRefreshToken(userDto);
                // Update the stored refresh token for the user
                refreshStorage.put(userDto.getEmail(), newRefreshToken);
                // Return a JwtResponse with the new access and refresh tokens
                return new JwtResponseDto(newAccessToken, newRefreshToken);
            }
        }
        // Throw an AuthException if validation fails
        throw new AuthException("Invalid JWT token");
    }


    /**
     * Retrieves the authentication information from the security context.
     *
     * @return the JwtAuthentication object containing the authentication information.
     */
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    // добаляем нового пользователя
    public UserDTO createUser(UserDTO userCredentialsDto) {
        return userService.insertUser(userCredentialsDto);
    }
}
