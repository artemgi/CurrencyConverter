package com.example.currencyconverter.service;

import com.example.currencyconverter.dao.ExchangeRatesRepository;
import com.example.currencyconverter.dto.ConversionCurrencyDTO;
import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.ExchangeRates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private final ExchangeRatesRepository exchangeRatesRepository;

	public static Date getDateWithoutTimeUsingCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		while (dayOfWeek == Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		}
		return calendar.getTime();
	}

	public ExchangeRates parseCurrencyForXML() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("http://www.cbr.ru/scripts/XML_daily.asp");
		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName("Valute");

		Set<Currency> currencies = new HashSet<>();
		ExchangeRates exchangeRate = new ExchangeRates();
		Date date = dateFormat.parse(doc.getDocumentElement().getAttribute("Date"));

		if (exchangeRatesRepository.findByDate(date) != null) {
			return null;
		}

		exchangeRate.setDate(date);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Currency currency = new Currency();
				currency.setValuteId(element.getAttribute("ID"));
				currency.setNumCode(element.getElementsByTagName("NumCode").item(0).getTextContent());
				currency.setCharCode(element.getElementsByTagName("CharCode").item(0).getTextContent());
				currency.setNominal(Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent()));
				currency.setName(element.getElementsByTagName("Name").item(0).getTextContent());
				currency.setValue(new BigDecimal(element.getElementsByTagName("Value").item(0).getTextContent().replace(",", ".")));
				currencies.add(currency);
			}
		}

		exchangeRate.setCurrencies(currencies);
		return exchangeRate;
	}

	public BigDecimal convertCurrency(ConversionCurrencyDTO conversionCurrencyDTO) throws Exception {
		ExchangeRates exchangeRates = getExchangeRates();
		Map<String, Currency> currencies = exchangeRates.getCurrencies().stream()
				.collect(Collectors.toMap(Currency::getCharCode, Function.identity()));
		Currency sourceCurrency = currencies.get(conversionCurrencyDTO.getSourceCurrencyCode());
		Currency targetCurrency = currencies.get(conversionCurrencyDTO.getTargetCurrencyCode());
		BigDecimal sourceValue = sourceCurrency.getValue();
		BigDecimal targetValue = targetCurrency.getValue();
		int sourceNominal = sourceCurrency.getNominal();
		int targetNominal = targetCurrency.getNominal();
		BigDecimal sourceAmount = conversionCurrencyDTO.getSourceAmount();
		return sourceValue.divide(targetValue, RoundingMode.FLOOR)
				.multiply(BigDecimal.valueOf(targetNominal))
				.divide(BigDecimal.valueOf(sourceNominal), RoundingMode.FLOOR)
				.multiply(sourceAmount);
	}

	private ExchangeRates getExchangeRates() throws Exception {
		ExchangeRates exchangeRates = exchangeRatesRepository.findByDate(getDateWithoutTimeUsingCalendar());
		if (exchangeRates == null) {
			exchangeRates = parseCurrencyForXML();
			if (exchangeRates == null) {
				log.error("???? ?????????????? ???????? ?????? ???????????????????? ???? ???? ???? ??????????????, ???????? ?????????? ?????????????? ???? ?????????????????? ????????");
				exchangeRates = exchangeRatesRepository.findFirstByOrderByDate();
			}
		}
		return exchangeRates;
	}

	@Cacheable(value = "currencies")
	public Set<Currency> getAllCurrenciesByCurrentDate() {
		ExchangeRates exchangeRates = exchangeRatesRepository.findByDate(getDateWithoutTimeUsingCalendar());
		if(exchangeRates == null){
			exchangeRates = exchangeRatesRepository.findFirstByOrderByDate();
		}
		return exchangeRates.getCurrencies();
	}
}
