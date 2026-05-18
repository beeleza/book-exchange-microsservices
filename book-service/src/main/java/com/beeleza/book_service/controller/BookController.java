package com.beeleza.book_service.controller;

import com.beeleza.book_service.dto.Exchange;
import com.beeleza.book_service.environment.InstanceInformationService;
import com.beeleza.book_service.model.Book;
import com.beeleza.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository bookRepository;

    // http://localhost:8100/book-service/1/BRL
    @GetMapping(value = "{id}/{currency}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable Long id,
            @PathVariable String currency
    ) {
        String port = informationService.retrieveServerPort();

        var book = bookRepository.findById(id).orElseThrow();

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate()
                .getForEntity("http://localhost:8000/exchange-service/" + "{amount}/{from}/{to}", Exchange.class, params);

        Exchange exchange = response.getBody();

        book.setEnvironment(port);
        book.setPrice(exchange.getConvertedValue());
        book.setCurrency(currency);

        return book;
    }
}
