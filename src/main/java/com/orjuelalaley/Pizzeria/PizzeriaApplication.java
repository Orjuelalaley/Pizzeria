package com.orjuelalaley.Pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Esta es la clase que ejecuta la aplicación
 * Esta clase tiene habilitado el uso de JPARespositories
 */
@SpringBootApplication
@EnableJpaRepositories
public class PizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}
}
