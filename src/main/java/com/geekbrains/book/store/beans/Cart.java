package com.geekbrains.book.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Data
@AllArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
//    private BookService bookService;


//    private List<OrderItem> orderItems = new ArrayList<>();
//
//    public void add(Long bookId) {
////        Book curBook = bookService.findById(bookId);
//        boolean contains = false;
//
//        for (OrderItem orderItem : orderItems) {
//            if (orderItem.getBook_id().equals(bookId)) {
//                contains = true;
//                orderItem.increaseCountByOne();
//            }
//        }
//
//        if (!contains) {
//            orderItems.add(new OrderItem(bookId));
//        }
//
//    }

}
