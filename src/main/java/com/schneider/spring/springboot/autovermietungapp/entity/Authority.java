package com.schneider.spring.springboot.autovermietungapp.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a user's authority in the system.
 * Each authority grants certain permissions to users.
 */
@Getter
@Setter
@Entity
@Table(name = "authorities")
@Schema(description = "Entity representing a user's authority in the system. Used to define roles and permissions.")
public class Authority implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    @Schema(description = "Unique identifier for the authority", example = "1", required = true)
    private int id;

    @Column(name = "authority_name")
    @Schema(description = "The name of the authority, such as 'READ_PRIVILEGES' or 'WRITE_PRIVILEGES'", example = "READ_PRIVILEGES", required = true)
    private String authorityName;

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    @Schema(description = "Roles associated with this authority", required = true)
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
