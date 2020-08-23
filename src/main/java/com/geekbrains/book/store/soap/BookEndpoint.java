package com.geekbrains.book.store.soap;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.soap.book.GetBookRequest;
import com.geekbrains.book.store.soap.book.GetBookResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@AllArgsConstructor
public class BookEndpoint {
    public static final String NAMESPACE_URI = "http://geekbrains.com/book/store/soap/book";

    private BookService bookService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getAllBooks(@RequestPayload GetBookRequest request) {
        GetBookResponse getBookResponse = new GetBookResponse();

        if (request.getArg().equals("all")){
            for (Book book : bookService.findAll()){
                com.geekbrains.book.store.soap.book.Book book1 = new com.geekbrains.book.store.soap.book.Book();
                book1.setTitle(book.getTitle());
                book1.setDescription(book.getDescription());
                book1.setPrice(book.getPrice());
                book1.setPublishYear(book.getPublishYear());

                getBookResponse.getBook().add(book1);
            }
        }

        return getBookResponse;
    }
}
