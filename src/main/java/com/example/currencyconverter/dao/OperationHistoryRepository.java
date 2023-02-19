package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {

}
