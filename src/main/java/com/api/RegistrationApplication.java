package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class RegistrationApplication {

	public static void main(String[] args) {

		SpringApplication.run(RegistrationApplication.class, args);
	}

}
