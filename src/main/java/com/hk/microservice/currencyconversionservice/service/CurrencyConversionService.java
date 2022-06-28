package com.hk.microservice.currencyconversionservice.service;

import com.hk.microservice.currencyconversionservice.controller.CurrencyExchangeProxy;
import com.hk.microservice.currencyconversionservice.model.CurrencyConversionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;


@Service
public class CurrencyConversionService {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyConversionModel calculate(String from, String to, BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrencyConversionModel> responseEntity =
                restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionModel.class, uriVariables);

        CurrencyConversionModel currency = responseEntity.getBody();

        CurrencyConversionModel calculatedCurrency = new CurrencyConversionModel(currency.getId(), currency.getFrom(), currency.getTo(),
                currency.getConversionMultiple(), quantity, quantity.multiply(currency.getConversionMultiple()),
                currency.getEnvironment());

        return calculatedCurrency;
    }

    public CurrencyConversionModel calculateFeign(String from, String to, BigDecimal quantity) {

        CurrencyConversionModel currency = currencyExchangeProxy.retrieveExchangeValue(from, to);

        CurrencyConversionModel calculatedCurrency = new CurrencyConversionModel(currency.getId(), currency.getFrom(), currency.getTo(),
                currency.getConversionMultiple(), quantity, quantity.multiply(currency.getConversionMultiple()),
                currency.getEnvironment() + "-feign");


        return calculatedCurrency;
    }

}
