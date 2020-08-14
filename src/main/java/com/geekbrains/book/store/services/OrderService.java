package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepo orderRepo;

    public Order saveOrUpdate(Order order) {
        return orderRepo.save(order);
    }
}
