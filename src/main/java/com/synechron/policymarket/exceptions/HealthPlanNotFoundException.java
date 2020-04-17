package com.synechron.policymarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HealthPlanNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public HealthPlanNotFoundException() {
		super();
	}

	public HealthPlanNotFoundException(String message) {
		super();
		this.message = message;
	}
}
