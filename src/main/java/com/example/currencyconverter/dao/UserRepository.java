package com.example.currencyconverter.dao;

import com.example.currencyconverter.model.OperationHistory;
import com.example.currencyconverter.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	@Override
	void delete(User user);
}
