package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
