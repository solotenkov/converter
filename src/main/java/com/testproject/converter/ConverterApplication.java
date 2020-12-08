package com.testproject.converter;

import com.testproject.converter.dao.CurrenciesRepository;
import com.testproject.converter.domain.Currency;
import com.testproject.converter.services.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@EnableCaching
@SpringBootApplication
public class ConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}
}
