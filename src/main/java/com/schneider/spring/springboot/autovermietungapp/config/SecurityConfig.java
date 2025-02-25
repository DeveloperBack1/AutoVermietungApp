package com.schneider.spring.springboot.autovermietungapp.config;

import com.schneider.spring.springboot.autovermietungapp.security.UserDetailsServiceImpl;
import com.schneider.spring.springboot.autovermietungapp.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig class handles all security-related configurations for the AutoVermietungApp.
 * <p>
 * This class configures JWT authentication, role-based access control, and disables unnecessary
 * security features like form-based login and HTTP Basic authentication.
 * </p>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtfilter;

    /**
     * Configures the HTTP security settings, including request authorization rules,
     * JWT filter, CSRF disabling, and session management.
     *
     * @param http the HttpSecurity object for configuring web security
     * @return the configured SecurityFilterChain
     * @throws Exception if there is any issue with security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout.deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                        // Public access for API documentation and login endpoint
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/auth/login").permitAll()
                        // Access for users with ROLE_USER or READ_PRIVILEGES
                        .requestMatchers(HttpMethod.GET, "/cars/getByBrand/{brand}").hasAnyAuthority("ROLE_USER", "READ_PRIVILEGES")
                        // Access for admins with ROLE_ADMIN or WRITE_PRIVILEGES
                        .requestMatchers(HttpMethod.GET, "/cars/getByModel/{model}").hasAnyAuthority("ROLE_ADMIN", "WRITE_PRIVILEGES")
                        .requestMatchers(HttpMethod.POST, "/cars/create").hasAnyAuthority("ROLE_ADMIN", "WRITE_PRIVILEGES")
                        .requestMatchers(HttpMethod.DELETE, "/cars/{id}").hasAnyAuthority("ROLE_ADMIN", "WRITE_PRIVILEGES")
                        // Public access for retrieving all cars
                        .requestMatchers(HttpMethod.GET, "/cars/getAll").permitAll()
                        // Any other requests require authentication
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /**
     * Provides a password encoder bean using BCrypt hashing algorithm.
     *
     * @return PasswordEncoder for encoding passwords securely
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a custom authentication provider using the application's UserDetailsService
     * and a BCrypt password encoder.
     *
     * @return AuthenticationProvider for handling user authentication
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Provides an authentication manager for managing authentication processes.
     *
     * @param authenticationConfiguration the authentication configuration
     * @return AuthenticationManager for handling authentication
     * @throws Exception if an issue occurs during the setup
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
