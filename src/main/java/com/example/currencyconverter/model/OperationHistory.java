package com.example.currencyconverter.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OperationHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String sourceCurrency;
	String targetCurrency;
	BigDecimal sourceAmount;
	BigDecimal targetAmount;
	Date date;
	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
