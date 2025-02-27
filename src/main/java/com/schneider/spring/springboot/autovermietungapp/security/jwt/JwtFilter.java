package com.schneider.spring.springboot.autovermietungapp.security.jwt;

import com.schneider.spring.springboot.autovermietungapp.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import static org.springframework.util.StringUtils.hasText;

/**
 * Filter class that intercepts incoming HTTP requests to check for valid JWT authentication tokens.
 * <p>
 * This class extends {@link OncePerRequestFilter} and is responsible for extracting the JWT
 * from the request, validating it, and setting the authenticated user in the Spring Security context.
 */

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Constructor that initializes the JWT utility and user details service.
     *
     * @param jwtUtils           The JWT utility to validate and parse the JWT token.
     * @param userDetailsService The service to load user details for authentication.
     */

    public JwtFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Intercepts the HTTP request to extract and validate the JWT token.
     * If the token is valid, the user authentication is set in the security context.
     *
     * @param request     The HTTP request to be processed.
     * @param response    The HTTP response to be sent.
     * @param filterChain The filter chain to proceed with after processing.
     * @throws ServletException if an error occurs during filtering.
     * @throws IOException      if an I/O error occurs.
     */

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getBody(jwt).getSubject();

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            LOG.error("Cannot set user authentication", e);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the "Authorization" header of the HTTP request.
     *
     * @param request The HTTP request to extract the token from.
     * @return The JWT token if found and valid, otherwise null.
     */

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}