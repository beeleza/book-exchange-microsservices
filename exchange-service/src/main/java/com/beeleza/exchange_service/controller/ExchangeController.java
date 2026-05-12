package com.beeleza.exchange_service.controller;

import com.beeleza.exchange_service.environment.InstanceInformationService;
import com.beeleza.exchange_service.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

    @Autowired
    private InstanceInformationService informationService;

    // http://localhost:8000/exchange-service/5/USD/BRL
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to) {
        return new Exchange(1L, from, to, BigDecimal.ONE, BigDecimal.ONE, "PORT " + informationService.retrieveServerPort());
    }
}
