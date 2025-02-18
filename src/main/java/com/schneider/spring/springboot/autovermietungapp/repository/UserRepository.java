package com.schneider.spring.springboot.autovermietungapp.repository;

import com.schneider.spring.springboot.autovermietungapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
