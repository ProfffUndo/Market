package com.submarine29.market.services;

import com.submarine29.market.domain.*;
import com.submarine29.market.repo.OrderItemRepo;
import com.submarine29.market.repo.OrderRepo;
import com.submarine29.market.repo.ProductRepo;
import com.submarine29.market.repo.UserDetailsRepo;
import org.hibernate.Session;
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

    public void addProductToOrder(Long userId,Long productId) throws SecurityException {
        try {
            User user = userRepo.findById(userId).get();
            List<Order> usersOrders = orderRepo.findByUser(user);
            Product product = productRepo.findById(productId).get();
            Iterator<Order> iter = usersOrders.listIterator();
            Order currentOrder;
            OrderItem item = new OrderItem();
            while ((currentOrder = iter.next()).getStatus() != Status.NEW && iter.hasNext()) {
            }
            if (currentOrder.getStatus() != Status.NEW) {
                currentOrder = new Order();
                orderRepo.save(currentOrder);
                List<OrderItem> itemList=new ArrayList<>();
                itemList.add(item);
                currentOrder.setOrderItems(itemList);//List.of() плохая идея, если у кого-то вдруг потянутся руки к нему
                currentOrder.setStatus(Status.NEW);
                currentOrder.setUser(user);
                currentOrder.setOrderDate(LocalDateTime.now());
            }
            item.setProduct(product);
            item.setOrder(currentOrder);
            orderItemRepo.save(item);

        } catch (NoSuchElementException e) {
            throw new SecurityException("Пользователь не авторизован", e);
        }

    }

    public Long getBasketOrder(Long user_id){
        User user = userRepo.findById(user_id).get();
        List<Order> usersOrders = orderRepo.findByUser(user);
        Iterator<Order> iter = usersOrders.listIterator();
        Order basketOrder;
        while ((basketOrder = iter.next()).getStatus() != Status.NEW && iter.hasNext()) {
        }
        if(basketOrder.getStatus()== Status.NEW)
            return basketOrder.getId();
        else
            return null;
    }

    public void deleteProductFromOrder(Long userId, Long productId) throws SecurityException {
        try {
            User user = userRepo.findById(userId).get();
            //orderItemRepo.delete(orderItem);
            List<Order> usersOrders = orderRepo.findByUser(user);
            Iterator<Order> iter = usersOrders.listIterator();
            Order currentOrder;
            while ((currentOrder = iter.next()).getStatus() != Status.NEW && iter.hasNext()) {
            }
            List <OrderItem> orderItems = orderItemRepo.findByOrder(currentOrder);

            for (OrderItem orderItem: orderItems){
                if (orderItem.getProduct().getId().equals(productId)){
                    orderItemRepo.delete(orderItem);
                    orderItems.remove(orderItem);
                    break;
                }
            }

            if (orderItems.isEmpty()){
                //currentOrder.setStatus(Status.DELETED);
                //orderRepo.save(currentOrder);
                orderRepo.delete(currentOrder);
            }

        } catch (NoSuchElementException e) {
            throw new SecurityException("Пользователь не авторизован", e);
        }
    }
}