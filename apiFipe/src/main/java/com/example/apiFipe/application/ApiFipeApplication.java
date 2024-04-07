package com.example.apiFipe.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.apiFipe.repository")
public class ApiFipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFipeApplication.class, args);
	}

}
