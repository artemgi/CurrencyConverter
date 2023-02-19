package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Long> {
	ExchangeRates findByDate(Date date);
}
