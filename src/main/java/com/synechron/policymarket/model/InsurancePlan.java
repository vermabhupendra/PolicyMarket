package com.synechron.policymarket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_insurance_plan")
@AllArgsConstructor
@Component
@NoArgsConstructor
@Data
public class InsurancePlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int planId;

	@NotBlank(message = "Plan name must not be empty.")
	@Size(min = 2, message = "Plan name should be at least 2 character long.")
	@NotNull(message = "Plan name must not be null.")
	private String planName;

	@NotBlank(message = "Insurance Provider name must not be empty.")
	@Size(min = 2, message = "Insurance Provider name should be at least 2 character long.")
	@NotNull(message = "Insurance Provider name must not be null.")
	private String providerName;

	@NotBlank(message = "Plan type must not be empty.")
	@Size(min = 2, message = "Plan type should be at least 2 character long.")
	@NotNull(message = "Plan type must not be null.")
	private String planType;

	@Min(value = 1)
	private double planAmount;

	@Min(value = 10_000)
	private double planCover;

	@Min(value = 5)
	private int age;

	@NotBlank(message = "City name must not be empty.")
	@Size(min = 2, message = "City name should be at least 2 character long.")
	@NotNull(message = "City name must not be null.")
	private String city;

	private int isActive;
}
