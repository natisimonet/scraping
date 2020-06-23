package org.ada.restapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="org.ada.restapi")
public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
		
		
	}

	
	}

