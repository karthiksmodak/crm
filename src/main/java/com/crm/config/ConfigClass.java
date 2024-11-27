package com.crm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//below class will run automatically when project is started
@Configuration
public class ConfigClass {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
