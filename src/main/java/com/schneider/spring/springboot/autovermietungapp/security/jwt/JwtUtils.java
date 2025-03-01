package com.schneider.spring.springboot.autovermietungapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static java.util.Collections.emptyMap;

/**
 * Utility class responsible for creating, parsing, and validating JWT tokens.
 * <p>
 * This class handles the generation of JWT tokens based on the authentication object,
 * and validation of JWT tokens to ensure their authenticity and expiration.
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${variables.jwtSecret}")
    private String jwtSecret;

    @Value("${variables.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Retrieves the secret key used for signing the JWT token.
     *
     * @return the signing key derived from the configured JWT secret.
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }

    /**
     * Generates a JWT token for the provided authentication object.
     *
     * @param authentication The authentication object containing user information.
     * @return The generated JWT token.
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        return generateToken(userPrincipal.getUsername(), emptyMap(), jwtExpirationMs);
    }

    /**
     * Generates a JWT token with a given subject and additional claims.
     *
     * @param subject  The subject (typically username) to include in the token.
     * @param claims   A map of additional claims to include in the token.
     * @param timeLive The expiration time in milliseconds for the token.
     * @return The generated JWT token.
     */
    public String generateToken(String subject, Map<String, Object> claims, int timeLive) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + timeLive))
                .signWith(getSigningKey(), Jwts.SIG.HS512)
                .compact();
    }

    /**
     * Parses the body of a JWT token.
     *
     * @param token The JWT token to parse.
     * @return The claims in the token body.
     */
    public Claims getBody(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Validates the JWT token to ensure it is properly signed and not expired.
     *
     * @param authToken The JWT token to validate.
     * @return {@code true} if the token is valid, otherwise {@code false}.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            logger.warn("JWT token is expired: {}", e.getMessage());
        } catch (Exception e) {
            logger.warn("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }
}