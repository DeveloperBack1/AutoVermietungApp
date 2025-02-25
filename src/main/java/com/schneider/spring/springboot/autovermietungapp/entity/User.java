package com.schneider.spring.springboot.autovermietungapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a user in the Autovermietung system.
 * This entity implements Spring Security's UserDetails interface to integrate with the security system.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@Schema(description = "Entity representing a user in the Autovermietung system.")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(description = "Unique identifier for the user", example = "1", required = true)
    private int id;

    @Column(name = "user_name")
    @Schema(description = "The full name of the user", example = "John Doe", required = true)
    private String name;

    @Column(name = "user_email")
    @Schema(description = "The email address of the user", example = "john.doe@example.com", required = true)
    private String email;

    @Column(name = "user_password")
    @Schema(description = "The password of the user (hashed and stored securely)", example = "hashed_password", required = true)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Schema(description = "Roles assigned to the user, determining their permissions.")
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Returns the authorities (roles) assigned to the user.
     *
     * @return the roles assigned to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    /**
     * Returns the username of the user (email in this case).
     *
     * @return the user's email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account is expired.
     *
     * @return true if the account is not expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     *
     * @return true if the account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are expired.
     *
     * @return true if the credentials are not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is enabled.
     *
     * @return true if the account is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
