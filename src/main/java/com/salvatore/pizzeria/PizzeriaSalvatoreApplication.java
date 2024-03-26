package com.salvatore.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PizzeriaSalvatoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaSalvatoreApplication.class, args);
	}

}
