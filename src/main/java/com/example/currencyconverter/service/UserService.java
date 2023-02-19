package com.example.currencyconverter.service;

import com.example.currencyconverter.dao.UserRepository;
import com.example.currencyconverter.dto.OperationHistoryDTO;
import com.example.currencyconverter.model.OperationHistory;
import com.example.currencyconverter.model.User;
import com.example.currencyconverter.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public User findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	public void createUser(User user) {
		String username = user.getUsername();
		if (userRepository.findByUsername(username) != null) return;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.getRoles().add(Role.ROLE_USER);
		log.info("Saving new User with email: {}", username);
		userRepository.save(user);
	}


}
