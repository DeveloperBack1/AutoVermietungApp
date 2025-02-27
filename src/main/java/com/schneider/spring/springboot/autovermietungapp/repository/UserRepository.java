package com.schneider.spring.springboot.autovermietungapp.repository;

import com.schneider.spring.springboot.autovermietungapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entity that provides CRUD operations and custom queries.
 * <p>
 * This interface extends {@link JpaRepository} to provide standard CRUD functionality
 * and includes a custom method for querying {@link User} entities by email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a {@link User} entity by their email.
     *
     * @param email the email address of the user to be queried.
     * @return an {@link Optional} containing the found {@link User} or empty if not found.
     */
    Optional<User> findUserByEmail(String email);
}