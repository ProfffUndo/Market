package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User implements UserDetails, Serializable {

    /**
     * ВНИМАТЕЛЬНО!!!
     * Я не знаю, почему изначально класс был сериализуем. Но допустим.
     * Я его изменил. Если убрать это, он не будет работать, потому что несовпадение со старой версией.
     * Либо я что-то не так понял.
     */
    private static final long serialVersionUID = -678788959254984266L;

    @Id
    private Long id;
    private String name;
    private String userpic; //Почему String?
    private String email;
    private String gender;
    private String locale;
    private String login;
    private String password;
    private String phone;
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Order> orders;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isManager() {
        return roles.contains(Role.MANAGER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
