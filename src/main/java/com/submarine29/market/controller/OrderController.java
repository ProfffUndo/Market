package com.submarine29.market.controller;

import com.submarine29.market.domain.Order;
import com.submarine29.market.domain.OrderItem;
import com.submarine29.market.domain.Product;
import com.submarine29.market.repo.OrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public List<Order> list() {
        return orderRepo.findAll();
    }

    @GetMapping("{id}")
    public Order getOne(@PathVariable("id") Order order) {
        return order;
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderRepo.save(order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Order order) {
        orderRepo.delete(order);
    }

    @PutMapping("{id}")
    public Order update(@PathVariable("id") Order orderFromDB, @RequestBody Order orderNew) {
        BeanUtils.copyProperties(orderNew, orderFromDB, "id");
        return orderRepo.save(orderFromDB);
    }
}
