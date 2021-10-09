package com.submarine29.market.repo;

import com.submarine29.market.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
