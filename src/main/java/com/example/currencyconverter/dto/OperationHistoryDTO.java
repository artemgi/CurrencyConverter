package com.example.currencyconverter.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationHistoryDTO {
	String parentCurrency;
	String targetCurrency;
	BigDecimal parentAmount;
	BigDecimal targetAmount;
}
