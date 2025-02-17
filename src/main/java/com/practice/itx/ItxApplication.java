package com.practice.itx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.practice.itx.infrastructure.persistence")
@SpringBootApplication
public class ItxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItxApplication.class, args);
	}

}
