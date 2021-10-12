package com.rest.microservices.currencyexchangeservice;

import com.rest.microservices.currencyexchangeservice.controllers.vo.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    public CurrencyExchange findByFromAndTo(String from, String to);
}
