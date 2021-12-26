package com.submarine29.market.repo;

import com.submarine29.market.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long s);
}
