package com.example.currencyconverter.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Currency {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String valuteId;
	private String numCode;
	private String charCode;
	private Integer nominal;
	private String name;
	@Column(precision = 17, scale = 4)
	private BigDecimal value;

//	@ManyToOne
//	private ExchangeRates exchangeRates;
}
