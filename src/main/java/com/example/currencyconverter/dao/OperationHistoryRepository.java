package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.OperationHistory;
import com.example.currencyconverter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
	List<OperationHistory> findByUser(User user);
}
