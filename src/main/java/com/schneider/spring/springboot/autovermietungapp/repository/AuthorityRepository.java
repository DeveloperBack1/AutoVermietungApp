package com.schneider.spring.springboot.autovermietungapp.repository;

import com.schneider.spring.springboot.autovermietungapp.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
