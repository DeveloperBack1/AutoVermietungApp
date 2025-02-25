package com.schneider.spring.springboot.autovermietungapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a role within the system.
 * <p>
 * This entity defines roles for users and their associated authorities (permissions).
 */
@Getter
@Setter
@Entity
@Table(name = "roles")
@Schema(description = "Represents a user role with associated authorities")
public class Role implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @Schema(description = "Unique identifier for the role", example = "1")
    private int id;

    @Column(name = "role_name")
    @Schema(description = "The name of the role (e.g., ADMIN, USER)", example = "ADMIN")
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roles_authorities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @Schema(description = "Authorities (permissions) associated with the role")
    private Set<Authority> authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + roleName + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
