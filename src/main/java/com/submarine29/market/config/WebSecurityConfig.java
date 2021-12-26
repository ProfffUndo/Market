package com.submarine29.market.config;

import com.submarine29.market.domain.Role;
import com.submarine29.market.domain.User;
import com.submarine29.market.repo.UserDetailsRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/", "/products").permitAll() //Разрещаем заходить на главную всем
                    .anyRequest().authenticated() //По другим запросам только с аунтификацией
                .and()
                    .logout()
                    .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        return map -> {
            String stringId = (String) map.get("sub");
            Long id = Long.parseLong(stringId.substring(8)) ; //Получаем ID
            User user = userDetailsRepo.findById(id).orElseGet(() -> {  //Если нашли пользователя в БД - возвращаем юзера по ИД, иначе новый юзер
                User newUser = new User();
                newUser.setId(id);
                newUser.setLogin((String) map.get("login"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserpic((String) map.get("picture"));
                newUser.setRoles(roleSet);

                return newUser;
            });

            user.setLastVisit(LocalDateTime.now());

            return userDetailsRepo.save(user);
        };
    }
}
