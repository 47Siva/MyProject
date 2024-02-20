package com.sheryians.major.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonConfig {

		@Bean
		public ObjectMapper objectMapper() {
			return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		}
	
}
