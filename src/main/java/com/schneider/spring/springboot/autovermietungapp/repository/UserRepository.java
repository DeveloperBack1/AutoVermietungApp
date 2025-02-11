package com.schneider.spring.springboot.autovermietungapp.repository;

import com.schneider.spring.springboot.autovermietungapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
