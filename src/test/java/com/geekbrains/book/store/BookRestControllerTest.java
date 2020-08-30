package com.geekbrains.book.store;

import com.geekbrains.book.store.configs.SecurityConfig;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
//import static org.springframework.boot.system.SystemProperties.get;

//@WebMvcTest(controllers = BookRestControllerTest.class, excludeAutoConfiguration = SecurityConfig.class)
@WebMvcTest(BookRestControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooksTest(){
        List<Book> books = Arrays.asList(
                new Book(1L, "Title", "Desc", new BigDecimal("1000"), 1000, Book.Genre.DETECTIVE),
                new Book(2L, "Title2", "Desc2", new BigDecimal("2000"), 2000, Book.Genre.FANTASY),
                new Book(3L, "Title3", "Desc3", new BigDecimal("3000"), 3000, Book.Genre.FICTION)
        );


        given(bookService.findAll()).willReturn(books);

//        mvc.perform(get("/")).andDo();

    }
}
