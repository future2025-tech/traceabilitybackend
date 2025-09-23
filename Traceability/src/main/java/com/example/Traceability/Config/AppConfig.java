package com.example.Traceability.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	ModelMapper modeMapper() {
		 return new ModelMapper();
	}
}
