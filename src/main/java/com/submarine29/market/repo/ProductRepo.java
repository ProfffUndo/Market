package com.submarine29.market.repo;

import com.submarine29.market.domain.Category;
import com.submarine29.market.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByCategory(Category category);
    long countByCategory(Category category);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    Page<Product> findAll(Pageable pageable);

    Product findByName(String name);

    @Query(value = "SELECT * FROM Product where Product.price >= :fromp and Product.price <= :to", nativeQuery = true)
    Page<Product> findAllFromToPrice (Pageable pageable, @Param("fromp") double from, @Param("to") double to);

    @Query(value = "SELECT * FROM Product where Product.price >= :fromp and Product.price <= :to and Product.category_id = :category_id", nativeQuery = true)
    Page<Product> findAllFromToPriceAndCategory (Pageable pageable, @Param("fromp") double from, @Param("to") double to, @Param("category_id") long categoryId);

    @Query(value = "SELECT * FROM Product where Product.price >= :fromp and Product.price <= :to and Product.category_id = :category_id and product.name ILIKE '%'|| :product_name || '%'", nativeQuery = true)
    Page<Product> findAllFromToPriceAndCategoryAndSearch (Pageable pageable, @Param("fromp") double from, @Param("to") double to, @Param("category_id") long categoryId, @Param("product_name") String name);

    @Query(value = "SELECT * FROM Product where Product.price >= :fromp and Product.price <= :to and product.name ILIKE '%'|| :product_name || '%'", nativeQuery = true)
    Page<Product> findAllFromToPriceAndSearch (Pageable pageable, @Param("fromp") double from, @Param("to") double to, @Param("product_name") String name);
}
