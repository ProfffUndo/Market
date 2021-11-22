package com.submarine29.market.repo;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.OrderItem;
import com.submarine29.market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
    long countByOrder(Order order);
    List<OrderItem> findByOrder(Order order);

    @Override
    Optional<OrderItem> findById(Long aLong);

    long countByProduct(Product product);
}
