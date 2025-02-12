package com.schneider.spring.springboot.autovermietungapp.repository;
import com.schneider.spring.springboot.autovermietungapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByName(String name);
}