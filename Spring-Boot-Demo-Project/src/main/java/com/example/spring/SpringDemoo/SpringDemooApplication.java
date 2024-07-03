package com.example.spring.SpringDemoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemooApplication {
	public static void main(String[] args) {
	//	SpringApplication.run(SpringDemooApplication.class, args);
		SpringApplication app =new SpringApplication(SpringDemooApplication.class);
		app.addListeners(new CreateDynamoDbTable());
		app.run(args);
		
	}

}
