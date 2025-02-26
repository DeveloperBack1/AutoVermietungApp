package com.schneider.spring.springboot.autovermietungapp.security;

import com.schneider.spring.springboot.autovermietungapp.entity.Authority;
import com.schneider.spring.springboot.autovermietungapp.entity.Role;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.exception.errorMessages.ErrorMessage;
import com.schneider.spring.springboot.autovermietungapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Implementation of the {@link UserDetailsService} interface for loading user-specific data
 * from the database. This service is used by Spring Security during authentication
 * to retrieve user details based on the provided username (email).
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads user details by email.
     *
     * @param email the email address of the user to be loaded.
     * @return an instance of {@link UserDetails} containing user information and authorities.
     * @throws UsernameNotFoundException if the user with the specified email does not exist.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User currentUser;

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(ErrorMessage.USER_NOT_EXIST);
        } else {
            currentUser = optionalUser.get();
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .username(currentUser.getEmail())
                .password(currentUser.getPassword())
                .authorities(getAuthorities(currentUser.getRoles()))
                .accountLocked(!currentUser.isAccountNonLocked())
                .accountExpired(!currentUser.isAccountNonExpired())
                .credentialsExpired(!currentUser.isCredentialsNonExpired())
                .disabled(!currentUser.isEnabled())
                .build();
    }

    /**
     * Retrieves the authorities granted to the user based on their roles and authorities.
     *
     * @param roles the set of roles assigned to the user.
     * @return a collection of {@link GrantedAuthority} objects that represent the user's roles
     *         and specific authorities.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

            Set<Authority> authoritySet = role.getAuthorities();

            for (Authority authority : authoritySet) {
                authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
            }
        }
        return authorities;
    }
}