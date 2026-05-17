package com.yachtsshop.yachtcatalogservice;

import com.yachtsshop.yachtcatalogservice.model.Yacht;
import com.yachtsshop.yachtcatalogservice.repository.YachtRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YachtCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YachtCatalogServiceApplication.class, args);
	}

	// Adăugăm 3 iahturi în memorie la fiecare pornire a aplicației
	@Bean
	CommandLineRunner initDatabase(YachtRepository repository) {
		return args -> {
			repository.save(new Yacht("Ocean Explorer", 1500000.0));
			repository.save(new Yacht("Sea Breeze", 850000.0));
			repository.save(new Yacht("Luxury Cruiser", 3200000.0));
			System.out.println("---- Catalogul de iahturi a fost inițializat! ----");
		};
	}
}
