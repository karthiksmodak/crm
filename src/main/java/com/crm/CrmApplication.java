package com.crm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//The below class can also be called as configuration class
 @SpringBootApplication
public class CrmApplication {
	 public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
//	@Bean
//	public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
	//should use @Bean in configuration class only
}