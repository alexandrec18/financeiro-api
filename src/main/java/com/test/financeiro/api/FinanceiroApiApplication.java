package com.test.financeiro.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.test.financeiro.api.config.property.FinanceiroApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(FinanceiroApiProperty.class)
public class FinanceiroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApiApplication.class, args);
	}
}
