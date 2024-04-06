package com.example.apiClima.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.apiClima.repository")
public class ApiClimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClimaApplication.class, args);
	}

}
