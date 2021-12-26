package com.submarine29.market.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    private static final long serialVersionUID = -678788959254984266L;

    @Id
    private Long id;
    private String name;//не ставлю NotBlank, так как иначе не добавить товары в корзину
    private String surname;
    private String patronymic;
    @Email
    private String email;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Номер телефона введен не верно")
    private String phone;
    @Size(min = 3, max = 20)
    private String username;
    @Size(min = 3, max = 20)
    private String login; //! не применяется почему-то, выполните 151 строку в data.sql
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=().])(?=\\\\S+$).{8,20}$",
            message = "Пароль должен состоять из восьми или более символов латинского алфавита, " +
                    "содержать заглавные и строчные буквы, цифры и специальные символы @ # $ % ^ & - + = ( ) .")
    private String password;

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
        return login;
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
