package com.example.epharmacy_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EpharmacyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpharmacyBackendApplication.class, args);
	}

}
