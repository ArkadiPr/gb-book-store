package com.geekbrains.book.store.services;

import com.geekbrains.book.store.repositories.OrderItemRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderItemService {
    private OrderItemRepo orderItemRepo;


}
