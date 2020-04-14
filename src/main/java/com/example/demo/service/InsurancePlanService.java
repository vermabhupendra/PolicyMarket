package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.InsurancePlan;
import com.example.demo.repository.InsurancePlanRepo;

@Service
public class InsurancePlanService {

	@Autowired
	private InsurancePlanRepo insurancePlanRepo;

	public List<InsurancePlan> getAllPlans(int isActive) {
		return insurancePlanRepo.findByIsActive(isActive);
	}

	@Transactional
	public int deactivatePlan(int status, int planId) {
		return insurancePlanRepo.deactivatePlan(status, planId);
	}

	public InsurancePlan addPolicyPlans(InsurancePlan insurancePlan) {
		return insurancePlanRepo.save(insurancePlan);
	}

	public InsurancePlan updatePolicyPlans(InsurancePlan insurancePlan) {
		return insurancePlanRepo.saveAndFlush(insurancePlan);
	}

	public InsurancePlan checkForPlan(int planId) throws EntityNotFoundException {
		System.out.println("===Plan id:"+planId);
		return insurancePlanRepo.getOne(planId);
	}
}
