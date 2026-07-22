package com.alexis.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/// Se agregan las direcciones de los demas paquetes que queremos que igual se ejecuten.
@SpringBootApplication(scanBasePackages = {"com.christian.medicos", "com.christian.commons"})
public class CommonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonsApplication.class, args);
	}

}
