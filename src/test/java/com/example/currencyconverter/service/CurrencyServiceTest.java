package com.example.currencyconverter.service;

import com.example.currencyconverter.dao.ExchangeRatesRepository;
import com.example.currencyconverter.dto.ConversionCurrencyDTO;
import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.ExchangeRates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Service
@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {
	public static final Date DATE = new Date();
	@Mock
	private ExchangeRatesRepository exchangeRatesRepository;
	@Mock
	private CurrencyService underTest;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest =  new CurrencyService(exchangeRatesRepository);
	}

	@Test
	void convertCurrency() throws Exception {
		ConversionCurrencyDTO conversionCurrencyDTO = new ConversionCurrencyDTO();
		conversionCurrencyDTO.setSourceCurrencyCode("USD");
		conversionCurrencyDTO.setTargetCurrencyCode("EUR");
		conversionCurrencyDTO.setSourceAmount(new BigDecimal("10"));

		Currency currencySource = new Currency();
		currencySource.setCharCode("USD");
		currencySource.setNominal(1);
		currencySource.setValue(new BigDecimal("74.2222"));
		Currency currencyTarget = new Currency();
		currencyTarget.setCharCode("EUR");
		currencyTarget.setNominal(1);
		currencyTarget.setValue(new BigDecimal("79.1111"));
		Set<Currency> currencies = new HashSet<>();
		currencies.add(currencySource);
		currencies.add(currencyTarget);
		ExchangeRates exchangeRates = new ExchangeRates();
		exchangeRates.setCurrencies(currencies);

		when(exchangeRatesRepository.findByDate(any())).thenReturn(exchangeRates);

		BigDecimal convertResult = underTest.convertCurrency(conversionCurrencyDTO);


		assertEquals(new BigDecimal("9.3820"), convertResult);
	}

	@Test
	void getAllCurrenciesByCurrentDate() {
		ExchangeRates exchangeRates = mock(ExchangeRates.class);
		when(exchangeRatesRepository.findByDate(any())).thenReturn(exchangeRates);
		when(exchangeRates.getCurrencies()).thenReturn(any());
		underTest.getAllCurrenciesByCurrentDate();
		verify(exchangeRatesRepository).findByDate(any());
	}
}