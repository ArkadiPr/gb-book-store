package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                               @RequestParam Map<String, String> params
    ) {
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);
        model.addAttribute("books", page.getContent());
        model.addAttribute("param", params);
//        model.addAttribute("maxPrice", page.getContent());

        model.addAttribute("pageHasNext", page.hasNext());
        model.addAttribute("pageHasPrevious", page.hasPrevious());
        model.addAttribute("nextPageNumber", page.nextOrLastPageable().getPageNumber());
        model.addAttribute("prevPageNumber", page.previousOrFirstPageable().getPageNumber());

//        for (Map.Entry<String, String> entry : params.entrySet()){
//            model.addAttribute(entry.getKey(), entry.getValue());
//        }

        return "store-page";
    }

    // Эта часть кода будет сильно скорректирована после темы Spring REST
    @GetMapping("/rest")
    @ResponseBody
    @CrossOrigin("*")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
