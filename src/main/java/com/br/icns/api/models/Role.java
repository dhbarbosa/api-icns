package com.br.icns.api.models;

import com.br.icns.api.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.management.relation.RoleNotFoundException;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority, Serializable {
    public static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName=roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
