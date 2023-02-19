package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.ExchangeRates;
import com.example.currencyconverter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	//Set<Currency> findByExchangeRatesId(Long exchangeRatesId);

}
