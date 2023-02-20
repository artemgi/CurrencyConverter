package com.example.currencyconverter.service;

import com.example.currencyconverter.dao.OperationHistoryRepository;
import com.example.currencyconverter.dto.ConversionCurrencyDTO;
import com.example.currencyconverter.model.OperationHistory;
import com.example.currencyconverter.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationHistoryService {
	private final OperationHistoryRepository operationHistoryRepository;
	public List<OperationHistory> getAllOperationHistory() {
		return operationHistoryRepository.findAll();
	}

	public void saveOperation(User user, ConversionCurrencyDTO conversionCurrencyDTO, BigDecimal targetCurrencyAmount) {
		OperationHistory operationHistory = new OperationHistory();
		operationHistory.setUser(user);
		operationHistory.setSourceCurrency(conversionCurrencyDTO.getSourceCurrencyCode());
		operationHistory.setTargetCurrency(conversionCurrencyDTO.getTargetCurrencyCode());
		operationHistory.setSourceAmount(conversionCurrencyDTO.getSourceAmount());
		operationHistory.setTargetAmount(targetCurrencyAmount);
		operationHistoryRepository.save(operationHistory);
	}

}
