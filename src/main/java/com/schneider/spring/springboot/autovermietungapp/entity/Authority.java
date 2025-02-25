package com.schneider.spring.springboot.autovermietungapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a system authority.
 * <p>
 * This entity is used to define user authorities and roles for access control.
 */
@Generated("Excluded from Jacoco coverage")
@Getter
@Setter
@Entity
@Table(name = "authorities")
@Schema(description = "Represents a system authority with specific permissions")
public class Authority implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    @Schema(description = "Unique identifier for the authority", example = "1")
    private int id;

    @Column(name = "authority_name")
    @Schema(description = "Name of the authority (e.g., ROLE_ADMIN)", example = "ROLE_USER")
    private String authorityName;

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    @Schema(description = "Roles associated with this authority")
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return id == authority.id && Objects.equals(authorityName, authority.authorityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorityName);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }
}
