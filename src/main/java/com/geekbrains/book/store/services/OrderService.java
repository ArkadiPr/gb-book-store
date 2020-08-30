package com.geekbrains.book.store.services;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepo orderRepo;

    public Order saveOrUpdate(Order order) {
        return orderRepo.save(order);
    }

    @Transactional
    public void makeOrder(Cart cart, User user){
        if (cart.getOrderItems().isEmpty()){
            throw new ResourceNotFoundException("orderItems list is empty");
        }

        Order newOrder = new Order(user);
        for (OrderItem orderItem: cart.getOrderItems()){
            orderItem.setOrder(newOrder);
        }

        newOrder.setOrderItems(cart.getOrderItems());

        saveOrUpdate(newOrder);
        cart.getOrderItems().clear();
    }
}
