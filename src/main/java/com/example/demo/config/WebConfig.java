package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.InsurancePlan;

@Configuration
public class WebConfig {

	@Bean
	public InsurancePlan user() {
		return new InsurancePlan();
	}
}
