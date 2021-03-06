package com.synechron.policymarket.exceptions;

import lombok.Data;

@Data
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
