package com.hk.microservice.currencyconversionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class CurrencyConversionModel {

    @Id
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private BigDecimal conversionMultiple;

    private BigDecimal quantity;

    private BigDecimal totalCalculatedAmount;

    private String environment;

}
