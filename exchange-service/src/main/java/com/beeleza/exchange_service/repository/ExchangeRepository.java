package com.beeleza.exchange_service.repository;

import com.beeleza.exchange_service.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Exchange findByFromAndTo(String from, String to);
}
