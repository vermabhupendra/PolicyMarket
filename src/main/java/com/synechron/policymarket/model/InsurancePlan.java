package com.synechron.policymarket.model;

import java.io.Serializable;

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
public class InsurancePlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int planId;

	@NotBlank(message = "Plan name must not be empty.")
	@Size(min = 2, message = "Plan name should be at least 2 character long.")
	@NotNull(message = "Plan name must not be null.")
	private String planName;

	@NotBlank(message = "Company name must not be empty.")
	@Size(min = 2, message = "Company name should be at least 2 character long.")
	@NotNull(message = "Company name must not be null.")
	private String companyName;

	@Min(value = 1)
	private double planAmount;

	@Min(value = 10_000)
	private double planCover;

	private int cashlessHospitals;

	@Min(value = 5)
	private int age;

	@NotBlank(message = "City name must not be empty.")
	@Size(min = 2, message = "City name should be at least 2 character long.")
	@NotNull(message = "City name must not be null.")
	private String city;

	private double premiumScore;

	private double claimSettlementRationScore;

	private double planBenefitsScore;

	private double finalPlanScore;

	private int isActive;

}
