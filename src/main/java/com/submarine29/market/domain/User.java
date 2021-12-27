package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
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
public class User implements UserDetails {

    /**
     * ВНИМАТЕЛЬНО!!!
     * Я не знаю, почему изначально класс был сериализуем. Но допустим.
     * Я его изменил. Если убрать это, он не будет работать, потому что несовпадение со старой версией.
     * Либо я что-то не так понял.
     */
    private static final long serialVersionUID = -678788959254984266L;

    @Id
    private Long id;
    @NotBlank
    private String name;
    @URL(message = "Введите ссылку на фотографию")
    private String userpic; //Почему String?
    @Email
    private String email;
    private String gender;
    private String locale;
    @Column(unique = true)
    private String login; //! не применяется почему-то, выполните 151 строку в data.sql
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=().])(?=\\\\S+$).{8,20}$",
            message = "Пароль должен состоять из восьми или более символов латинского алфавита, " +
                    "содержать заглавные и строчные буквы, цифры и специальные символы @ # $ % ^ & - + = ( ) .")
    private String password;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Номер телефона введен не верно")
    private String phone;
    @PastOrPresent
    private LocalDateTime lastVisit;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Order> orders;

    public boolean isAdmin() {
        if (roles != null) {
            return roles.contains(Role.ADMIN);
        }
        return false;
    }

    public boolean isManager() {
        if (roles != null) {
            return roles.contains(Role.MANAGER);
        }
        return false;
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

    public List<Order> getPaidOrders(){
        List<Order> orderList=this.getOrders();
        orderList.removeIf(t->t.getStatus()==Status.NEW);
        return orderList;
    }

}
