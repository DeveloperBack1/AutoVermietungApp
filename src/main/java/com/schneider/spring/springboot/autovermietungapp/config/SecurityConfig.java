package com.schneider.spring.springboot.autovermietungapp.config;

import com.schneider.spring.springboot.autovermietungapp.security.UserDetailsServiceImpl;
import com.schneider.spring.springboot.autovermietungapp.security.jwt.JwtFilter;
import com.schneider.spring.springboot.autovermietungapp.security.utility.SecurityPermission;
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
 * Security configuration for the Autovermietung project.
 * <p>
 * This class configures Spring Security to handle authentication, authorization,
 * and JWT token filtering for secure endpoints.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtfilter;

    /**
     * Configures HTTP security settings, including routes and their access rules.
     * <p>
     * This method specifies which API endpoints require authentication and what roles or
     * permissions are necessary to access them.
     *
     * @param http the HttpSecurity instance
     * @return configured SecurityFilterChain instance
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout.deleteCookies("JSESSIONID"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SecurityPermission.getSwaggerAndLoginPermissions()).permitAll()
                        .requestMatchers(HttpMethod.GET, "/cars/getByBrand/{brand}").hasAnyAuthority(SecurityPermission.getGetByBrandPermissions())
                        .requestMatchers(HttpMethod.GET, "/cars/getByModel/{model}").hasAnyAuthority(SecurityPermission.getGetByModelPermissions())
                        .requestMatchers(HttpMethod.POST, "/cars/create").hasAnyAuthority(SecurityPermission.getCarsCreatePermissions())
                        .requestMatchers(HttpMethod.DELETE, "/cars/delete/{id}").hasAnyAuthority(SecurityPermission.getCarsDeletePermissions())
                        .requestMatchers(HttpMethod.GET, "/cars/getAll").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /**
     * Bean for password encoding using BCrypt.
     *
     * @return PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for configuring the authentication provider.
     * <p>
     * The provider uses a custom UserDetailsService and password encoder for user authentication.
     *
     * @return AuthenticationProvider instance
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Bean for the authentication manager to handle the authentication process.
     *
     * @param authenticationConfiguration the AuthenticationConfiguration instance
     * @return AuthenticationManager instance
     * @throws Exception if there is a configuration error
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}