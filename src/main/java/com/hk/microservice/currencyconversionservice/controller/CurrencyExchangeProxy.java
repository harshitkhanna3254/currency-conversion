package com.hk.microservice.currencyconversionservice.controller;


import com.hk.microservice.currencyconversionservice.model.CurrencyConversionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "http://localhost:8000")
@FeignClient(name = "currency-exchange") //load balances automatically
public interface CurrencyExchangeProxy {

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionModel retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
