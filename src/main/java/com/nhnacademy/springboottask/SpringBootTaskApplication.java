package com.nhnacademy.springboottask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTaskApplication.class, args);
	}

}
