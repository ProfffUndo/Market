package com.submarine29.market.repo;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);

    @Override
    Optional<Order> findById(Long aLong);

    Order findByPaymentId(String paymentId);
}
