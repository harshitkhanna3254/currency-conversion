package com.hk.microservice.currencyconversionservice.controller;

import com.hk.microservice.currencyconversionservice.model.CurrencyConversionModel;
import com.hk.microservice.currencyconversionservice.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService currencyConversionService;


    @GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionModel calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionModel currencyConverted = currencyConversionService.calculate(from, to, quantity);

        return currencyConverted;
    }

    @GetMapping(path = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionModel calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionModel currencyConverted = currencyConversionService.calculateFeign(from, to, quantity);

        return currencyConverted;
    }

}
