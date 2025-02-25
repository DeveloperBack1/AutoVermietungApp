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
 * Entity representing a user in the system.
 * <p>
 * This entity is used for authentication and authorization in the system, including roles and authorities.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@Schema(description = "Represents a user in the system with authentication and roles")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(description = "Unique identifier for the user", example = "1")
    private int id;

    @Column(name = "user_name")
    @Schema(description = "The name of the user", example = "John Doe")
    private String name;

    @Column(name = "user_email")
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Column(name = "user_password")
    @Schema(description = "Password of the user")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Schema(description = "Roles assigned to the user")
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

    @Override
    @Schema(description = "Roles assigned to the user, used for permissions")
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    @Schema(description = "Returns the username (email) used for login")
    public String getUsername() {
        return email;
    }

    @Override
    @Schema(description = "Indicates whether the account is non-expired")
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Indicates whether the account is non-locked")
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Schema(description = "Indicates whether the user's credentials are non-expired")
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Indicates whether the user is enabled")
    public boolean isEnabled() {
        return true;
    }
}