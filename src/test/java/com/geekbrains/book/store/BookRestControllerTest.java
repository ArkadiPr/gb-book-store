package com.geekbrains.book.store;

import com.geekbrains.book.store.configs.SecurityConfig;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = BookRestControllerTest.class, excludeAutoConfiguration = SecurityConfig.class)
//@WebMvcTest(BookRestControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class BookRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> books = Arrays.asList(
                new Book(1L, "Title", "Desc", new BigDecimal("1000"), 1000, Book.Genre.DETECTIVE),
                new Book(2L, "Title2", "Desc2", new BigDecimal("2000"), 2000, Book.Genre.FANTASY),
                new Book(3L, "Title3", "Desc3", new BigDecimal("3000"), 3000, Book.Genre.FICTION)
        );


        given(bookService.findAll()).willReturn(books);

        mvc.perform(get("/api/v1/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title", is(books.get(0).getTitle())));

    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book testBook = new Book(1L, "Title", "Desc", new BigDecimal("1000"), 1000, Book.Genre.DETECTIVE);

        given(bookService.findById(1L)).willReturn(testBook);

        mvc.perform(get("/api/v1/books/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(testBook.getTitle())));
    }

}
