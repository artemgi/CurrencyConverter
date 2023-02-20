package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.ExchangeRates;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.xml.crypto.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExchangeRatesRepositoryTest {

	@Autowired
	private ExchangeRatesRepository underTest;

	@Test
	void findByDate() {
		Date dateTest = new Date();

		Currency currency = Mockito.mock(Currency.class);
		ExchangeRates exchangeRates = new ExchangeRates();
		exchangeRates.setCurrencies(Collections.singleton(currency));
		exchangeRates.setDate(dateTest);

		underTest.save(exchangeRates);
		assertEquals(exchangeRates, underTest.findByDate(dateTest));
		assertEquals(exchangeRates, underTest.findFirstByOrderByDate());

	}
}