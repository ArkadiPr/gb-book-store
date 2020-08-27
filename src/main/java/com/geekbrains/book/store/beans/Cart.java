package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private BookService bookService;
    private OrderService orderService;

    private List<OrderItem> orderItems = new ArrayList<>();

    public void add(Long bookId) {
        boolean contains = false;

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getBook().getId().equals(bookId)) {
                contains = true;
                orderItem.setAmount(orderItem.getAmount() + 1);
            }
        }

        if (!contains) {
            orderItems.add(new OrderItem(bookService.findById(bookId)));
        }
    }

    @Transactional
    public void makeOrder(User user){
        if (orderItems.isEmpty()){
            throw new ResourceNotFoundException("orderItems list is empty");
        }

        Order newOrder = new Order(user);
        for (OrderItem orderItem: orderItems){
            orderItem.setOrder(newOrder);
        }

        newOrder.setOrderItems(orderItems);
        newOrder.setStatus("processing");

        orderService.saveOrUpdate(newOrder);
        orderService.sendMessage(newOrder);
        orderItems.clear();
    }

}
