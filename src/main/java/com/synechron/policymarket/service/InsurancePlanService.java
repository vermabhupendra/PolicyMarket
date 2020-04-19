package com.synechron.policymarket.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.policymarket.model.InsurancePlan;
import com.synechron.policymarket.repository.InsurancePlanRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InsurancePlanService {

	@Autowired
	private InsurancePlanRepo insurancePlanRepo;

	public List<InsurancePlan> getAllActivePlans(int isActive) {
		return insurancePlanRepo.findByIsActive(isActive);
	}

	public List<InsurancePlan> getPlansByAgeAndCity(int age, String city) {
		return insurancePlanRepo.findByAgeAndCity(age, city);
	}

	public InsurancePlan addHealthPlan(InsurancePlan insurancePlan) {
		return insurancePlanRepo.saveAndFlush(insurancePlan);
	}

	public InsurancePlan updateHealthPlan(InsurancePlan insurancePlan) {
		return insurancePlanRepo.saveAndFlush(insurancePlan);
	}

	@Transactional
	public int deactivatePlan(int status, int planId) {
		return insurancePlanRepo.deactivatePlan(status, planId);
	}

	public InsurancePlan checkForHealthPlan(int planId) throws EntityNotFoundException {
		log.debug("Plan id:" + planId);
		return insurancePlanRepo.getOne(planId);
	}
}
