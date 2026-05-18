package com.beeleza.book_service.controller;

import com.beeleza.book_service.environment.InstanceInformationService;
import com.beeleza.book_service.model.Book;
import com.beeleza.book_service.proxy.ExchangeServiceProxy;
import com.beeleza.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExchangeServiceProxy exchangeServiceProxy;

    // http://localhost:8100/book-service/1/BRL
    @GetMapping(value = "{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable Long id,
            @PathVariable String currency
    ) {
        var book = bookRepository.findById(id).orElseThrow();

        var exchange = exchangeServiceProxy.getExchange(book.getPrice(), "USD", currency);

        book.setEnvironment(informationService.retrieveServerPort());
        book.setPrice(exchange.getConvertedValue());
        book.setCurrency(currency);

        return book;
    }
}
