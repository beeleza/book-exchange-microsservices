package com.beeleza.exchange_service.controller;

import com.beeleza.exchange_service.environment.InstanceInformationService;
import com.beeleza.exchange_service.model.Exchange;
import com.beeleza.exchange_service.repository.ExchangeRepository;
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

    @Autowired
    private ExchangeRepository repository;

    // http://localhost:8000/exchange-service/5/USD/BRL
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to) {
        Exchange exchange = repository.findByFromAndTo(from, to);

        if (exchange == null) throw new RuntimeException("Currency Unsupported!");

        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("PORT " + informationService.retrieveServerPort());

        return exchange;
    }
}
