package com.synechron.policymarket.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_insurance_plan")
@AllArgsConstructor
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
	private String planName;
	private String companyName;
	private double planAmount;
	private double planCover;
	private int cashlessHospitals;
	private int age;
	private String city;
	private double premiumScore;
	private double claimSettlementRationScore;
	private double planBenefitsScore;
	private double finalPlanScore;
	private int isActive;
//	private String createdDate;
//	private String modifiedDate;
//	private String createdBy;
//	private String modifiedBy;

}
