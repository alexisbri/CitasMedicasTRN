package com.alexis.medicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Escanea componentes fuera del proyecto actual indicando sus rutas.
@SpringBootApplication(scanBasePackages = {"com.alexis.medicos", "com.alexis.commons"})
public class MedicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicosApplication.class, args);
	}

}
