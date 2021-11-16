package com.submarine29.market.repo;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByCategory(Category category);
    long countByCategory(Category category);

    @Override
    Optional<Product> findById(Long aLong);
}
