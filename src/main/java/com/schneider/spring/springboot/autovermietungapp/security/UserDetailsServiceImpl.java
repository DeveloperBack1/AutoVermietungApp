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

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User currentUser;

        if(optionalUser.isEmpty()) {
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

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

            Set<Authority> authoritySet = role.getAuthorities();

            for(Authority authority : authoritySet) {
                authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
            }
        }
        return authorities;
    }
}