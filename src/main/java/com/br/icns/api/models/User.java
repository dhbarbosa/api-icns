package com.br.icns.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idUser;

    @Column(name = "username", nullable = false, length = 14, unique = true)
    private String username;

    @Column(name="name", nullable = false)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="dataCreate", nullable = false)
    private LocalDateTime dateCreate;

    @Column(name="dataLastUpdate")
    private LocalDateTime dataLastUpdate;

    @OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Notas> notas = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "idUser", referencedColumnName = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole", referencedColumnName = "idRole"))
    private Collection<Role> roles = new HashSet<>();

    public User(String username, String name, String password, LocalDateTime utc, List<Notas> notas, Set<Role> roles) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dateCreate = utc;
        this.notas = notas;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return this.roles;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
