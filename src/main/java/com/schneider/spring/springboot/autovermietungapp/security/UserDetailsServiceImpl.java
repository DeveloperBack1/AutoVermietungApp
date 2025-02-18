package com.schneider.spring.springboot.autovermietungapp.security;

import com.schneider.spring.springboot.autovermietungapp.entity.Authority;
import com.schneider.spring.springboot.autovermietungapp.entity.Role;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import com.schneider.spring.springboot.autovermietungapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Репозиторий для сущности User

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким email не найден"));

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            for (Authority authority : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}