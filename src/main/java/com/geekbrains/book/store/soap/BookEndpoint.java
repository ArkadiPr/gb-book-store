package com.geekbrains.book.store.soap;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.soap.book.GetBookRequest;
import com.geekbrains.book.store.soap.book.GetBookResponse;
import com.geekbrains.book.store.soap.book.Root;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
//@AllArgsConstructor
public class BookEndpoint {
//        public static final String NAMESPACE_URI = "http://www.example.com/store/ws/books";
    public static final String NAMESPACE_URI = "http://geekbrains.com/book/store/soap/book";

    @Autowired
    private BookService bookService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
//    public List<Root.GetBookResponse> getAllBooks(@RequestPayload Root.GetBookRequest request){
    public GetBookResponse getAllBooks(@RequestPayload GetBookRequest request) {
//        Root.GetBookResponse response = new Root.GetBookResponse();
//        List<Root.GetBookResponse> responses = new ArrayList<>();

//        if (request.getArg().equals("all")){
//            for (Book book : bookService.findAll()){
//                com.geekbrains.book.store.soap.book.Book book1 = new com.geekbrains.book.store.soap.book.Book();
//                book1.setTitle(book.getTitle());
//                book1.setDescription(book.getDescription());
//                book1.setPrice(book.getPrice());
//                book1.setPublishYear(book.getPublishYear());
//
//                Root.GetBookResponse getBookResponse = new Root.GetBookResponse();
//                getBookResponse.setBook(book1);
//                responses.add(getBookResponse);
//            }
//
//        }

        Book book = bookService.findById(1L);
        com.geekbrains.book.store.soap.book.Book book1 = new com.geekbrains.book.store.soap.book.Book();
        book1.setTitle(book.getTitle());
        book1.setDescription(book.getDescription());
        book1.setPrice(book.getPrice());
        book1.setPublishYear(book.getPublishYear());

//        Root.GetBookResponse getBookResponse = new Root.GetBookResponse();
        GetBookResponse getBookResponse = new GetBookResponse();
        getBookResponse.setBook(book1);
        return getBookResponse;
    }
}
