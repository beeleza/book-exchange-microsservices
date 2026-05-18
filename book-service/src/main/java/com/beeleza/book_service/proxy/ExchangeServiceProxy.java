package com.beeleza.book_service.proxy;

import com.beeleza.book_service.dto.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-service", url = "${exchange.service.url}")
public interface ExchangeServiceProxy {

    @GetMapping("/exchange-service/{amount}/{from}/{to}")
    Exchange getExchange(
            @PathVariable Double amount,
            @PathVariable String from,
            @PathVariable String to
    );
}
