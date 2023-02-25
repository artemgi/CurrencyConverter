package com.example.currencyconverter.controller;


import com.example.currencyconverter.dto.ConversionCurrencyDTO;
import com.example.currencyconverter.model.Currency;
import com.example.currencyconverter.model.OperationHistory;
import com.example.currencyconverter.model.User;
import com.example.currencyconverter.service.CurrencyService;
import com.example.currencyconverter.service.OperationHistoryService;
import com.example.currencyconverter.service.UserService;
import lombok.RequiredArgsConstructor;
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ConverterController {
	@Autowired
	private final CurrencyService currencyService;
	private final OperationHistoryService operationHistoryService;
	private final UserService userService;

	@GetMapping("/")
	public String hello() {
		return "hello";
	}

	@GetMapping("/converterPage")
	public String converter(@Qualifier Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		List<OperationHistory> operations = operationHistoryService.getOperationHistoryByUser(user);
		model.addAttribute("operations", operations);
		return "converterPage";
	}

	@PostMapping(value = "/converter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> convert(@RequestBody ConversionCurrencyDTO conversionCurrencyDTO) throws Exception {
		BigDecimal targetCurrencyAmount = currencyService.convertCurrency(conversionCurrencyDTO);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		if (user != null) {
			operationHistoryService.saveOperation(user, conversionCurrencyDTO, targetCurrencyAmount);
		}
		return new ResponseEntity<>(targetCurrencyAmount.toString(), HttpStatus.OK);
	}

	@GetMapping(value = "/currencies")
	public ResponseEntity<Set<Currency>> getAllCurrencies() {
		return new ResponseEntity<>(currencyService.getAllCurrenciesByCurrentDate(), HttpStatus.OK);
	}
}
