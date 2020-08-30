package com.geekbrains.book.store;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private BookService bookService;

    @Test
    public void CartAddTest(){
        Mockito.doReturn(new Book())
                .when(bookService)
                .findById(1L);

        cart.add(1L);

        Assertions.assertEquals(1, cart.getOrderItems().size());

    }

}
