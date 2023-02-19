package com.example.currencyconverter.service;

import com.example.currencyconverter.dao.CurrencyRepository;
import com.example.currencyconverter.dao.ExchangeRatesRepository;
import com.example.currencyconverter.model.ExchangeRates;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CurrencyInitializing implements InitializingBean {
	private final CurrencyService currencyService;
	private final ExchangeRatesRepository exchangeRatesRepository;

	@PostConstruct
	public void init() throws Exception {
		ExchangeRates exchangeRates = currencyService.parseCurrencyForXML();
		if (exchangeRates != null) {
			exchangeRatesRepository.save(exchangeRates);
		}
		System.out.println("InitializingBeanImpl afterPropertiesSet method called");
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("InitializingBeanImpl afterPropertiesSet method called");
	}
}
