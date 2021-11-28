package com.submarine29.market.repo;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Long aLong);

    Category findByName(String name);
}
