package com.submarine29.market.services;

import com.submarine29.market.domain.*;
import com.submarine29.market.repo.OrderItemRepo;
import com.submarine29.market.repo.OrderRepo;
import com.submarine29.market.repo.ProductRepo;
import com.submarine29.market.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserDetailsRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    public void addProductToOrder(Long userId, Long productId) throws SecurityException {
        try {
            User user = userRepo.findById(userId).get();
            List<Order> usersOrders = orderRepo.findByUser(user);
            Product product = productRepo.findById(productId).get();
            Iterator<Order> iter = usersOrders.listIterator();
            Order currentOrder;

            while ((currentOrder = iter.next()).getStatus() != Status.NEW && iter.hasNext()) {
            }
            if (currentOrder.getStatus() != Status.NEW) {
                OrderItem item = new OrderItem();
                currentOrder = new Order();
                List<OrderItem> itemList = new ArrayList<>();
                itemList.add(item);
                currentOrder.setOrderItems(itemList);//List.of() плохая идея, если у кого-то вдруг потянутся руки к нему
                currentOrder.setStatus(Status.NEW);
                currentOrder.setUser(user);
                currentOrder.setOrderDate(LocalDateTime.now());
                item.setProduct(product);
                item.setOrder(currentOrder);
                item.setCount(1L);
                orderRepo.save(currentOrder);
                orderItemRepo.save(item);
            } else {
                Iterator<OrderItem> itemIter = currentOrder.getOrderItems().listIterator();
                OrderItem itemFromCurrentOrder;
                while (itemIter.hasNext()) {
                    itemFromCurrentOrder = itemIter.next();
                    if (itemFromCurrentOrder.getProduct().equals(product)) {
                        itemFromCurrentOrder.setCount(itemFromCurrentOrder.getCount() + 1);
                        return;
                    }
                }
                OrderItem item = new OrderItem();
                item.setProduct(product);
                item.setOrder(currentOrder);
                item.setCount(1L);
                orderItemRepo.save(item);
            }
        } catch (NoSuchElementException e) {
            throw new SecurityException("Пользователь не авторизован", e);
        }

    }

    public Long getBasketOrder(Long user_id) {
        User user = userRepo.findById(user_id).get();
        List<Order> usersOrders = orderRepo.findByUser(user);
        Iterator<Order> iter = usersOrders.listIterator();
        Order basketOrder;
        while (iter.hasNext()) {
            basketOrder = iter.next();
            if (basketOrder.getStatus() == Status.NEW)
                return basketOrder.getId();
        }
        return null;
    }

    public void deleteProductFromOrder(Long userId, Long productId, boolean remove) throws SecurityException {
        try {
            User user = userRepo.findById(userId).get();
            List<Order> usersOrders = orderRepo.findByUser(user);
            Iterator<Order> iter = usersOrders.listIterator();
            Order currentOrder;
            while (iter.hasNext()) {
                currentOrder = iter.next();
                if (currentOrder.getStatus() == Status.NEW) {
                    List<OrderItem> orderItems = orderItemRepo.findByOrder(currentOrder);
                    for (OrderItem orderItem : orderItems) {
                        if (orderItem.getProduct().getId().equals(productId)) {
                            if(!remove) {
                                decreaseOrderItemCount(orderItem, orderItems);
                            }else{
                                orderItemRepo.delete(orderItem);
                                orderItems.remove(orderItem);
                            }
                            break;
                        }
                    }

                    if (orderItems.isEmpty()) {
                        orderRepo.delete(currentOrder);
                    }
                    break;
                }
            }

        } catch (NoSuchElementException e) {
            throw new SecurityException("Пользователь не авторизован", e);
        }
    }

    private void decreaseOrderItemCount(OrderItem orderItem, List<OrderItem> orderItems) {
        if (orderItem.getCount() > 1)
            orderItem.setCount(orderItem.getCount() - 1);
        else {
            orderItemRepo.delete(orderItem);
            orderItems.remove(orderItem);
        }

    }
}