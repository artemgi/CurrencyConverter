package com.example.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebApplication {
	public static void main(String[] args) throws Throwable {
		SpringApplication.run(WebApplication.class, args);
	}
}
