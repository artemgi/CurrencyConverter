package com.example.currencyconverter.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionCurrencyDTO {
	private String sourceCurrencyCode;
	private String targetCurrencyCode;
	private BigDecimal sourceAmount;
}
