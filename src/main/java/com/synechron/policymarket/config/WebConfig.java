package com.synechron.policymarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.synechron.policymarket.model.InsurancePlan;

@Configuration
public class WebConfig {

	@Bean
	public InsurancePlan insurancePlan() {
		return new InsurancePlan();
	}
}
