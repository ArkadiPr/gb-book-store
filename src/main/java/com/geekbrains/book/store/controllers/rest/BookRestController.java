package com.geekbrains.book.store.controllers.rest;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.dto.BookDto;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.pojo.BookGenre;
import com.geekbrains.book.store.pojo.FilterParams;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(
//            @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
//            @RequestParam Map<String, String> params
    ) {
//        BookFilter bookFilter = new BookFilter(params);
//        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);

        return bookService.findAll();
//        return page.toList();
//        return page.toList();
    }

    @GetMapping("/page")
    public Page<Book> getPage(
            @RequestParam(name = "p", defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "s", defaultValue = "5") Integer size,
            @RequestParam Map<String, String> params
    ) {
        BookFilter bookFilter = new BookFilter(params);
        //        return bookService.findAll();
        System.out.println("getted parms: p=" + pageIndex + "s=" + size);
        return bookService.findAll(bookFilter.getSpec(), pageIndex, size);
    }

//    @PostMapping("/filter")
//    public Page<Book> applyFilter(
//            @RequestBody FilterParams filterParams
//            ){
//
//        return null;
//    }

    @GetMapping("/genres")
//    public List<Book.Genre> getGenres() {
    public List<BookGenre> getGenres() {
        List<BookGenre> bookGenres= new ArrayList<>();

        for (Book.Genre g : Book.Genre.values()){
            bookGenres.add(new BookGenre(g.name()));
        }
//        return List.of(Book.Genre.values());
        return bookGenres;
    }

    @GetMapping("/dtos")
    public List<BookDto> getAllBooksDto() {
        return bookService.findAllDtos();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewBook(@RequestBody Book book) {
        if (book.getId() != null) {
            book.setId(null);
        }
        return bookService.saveOrUpdate(book);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Book modifyBook(@RequestBody Book book) {
        if (!bookService.existsById(book.getId())) {
            throw new ResourceNotFoundException("Book with id: " + book.getId() + " doesn't exists");
        }
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping
    public void deleteAllBooks() {
        bookService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
