package com.synechron.policymarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.policymarket.constants.Constants;
import com.synechron.policymarket.model.InsurancePlan;
import com.synechron.policymarket.service.InsurancePlanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "policymarket")
public class InsurancePlanController {

	@Autowired
	private InsurancePlanService insurancePlanService;

	/**
	 * Method to get all active health plans
	 * 
	 * @return
	 */
	@GetMapping("/plan/getAll")
	public List<InsurancePlan> getAllActivePlans() {
		return insurancePlanService.getAllActivePlans(Constants.ACTIVE);
	}

	/**
	 * Method to get Plans on the basis of Age and City
	 * 
	 * @param insurancePlan
	 * @return
	 */
	@PostMapping("/plan/getPlansByAgeAndCity")
	public List<InsurancePlan> getPlansByAgeAndCity(@RequestBody InsurancePlan insurancePlan) {
		return insurancePlanService.getPlansByAgeAndCity(insurancePlan);
	}

	/**
	 * Method to add/register new health plan
	 * 
	 * @param insurancePlan
	 * @return
	 */
	@PostMapping("/plan/add")
	public String addHealthPlan(@RequestBody InsurancePlan insurancePlan) {
		InsurancePlan result = insurancePlanService.addHealthPlan(insurancePlan);
		if (result.getPlanId() != 0) {
			return "Health Plan Registered Successfully";
		} else {
			return "Try Again";
		}
	}

	/**
	 * Method to update the existing health plan
	 * 
	 * @param insurancePlan
	 * @return
	 */
	@PutMapping("/plan/update")
	public String updateHealthPlan(@RequestBody InsurancePlan insurancePlan) {
		try {
			InsurancePlan plan = insurancePlanService.checkForHealthPlan(insurancePlan.getPlanId());
			log.debug("Plan = " + plan);
			if (plan != null) {
				InsurancePlan result = insurancePlanService.updateHealthPlan(insurancePlan);
				if (result.getPlanId() != 0) {
					return "Health Plan Updated Successfully";
				} else {
					return "Try Again";
				}
			} else {
				return "Health plan you are trying to update does not exists";
			}
		} catch (Exception e) {
			return "Health plan you are trying to update does not exists";
		}
	}
	
	/**
	 * Method to delete(deactivate) the health plan
	 * 
	 * @param planId
	 * @return
	 */
	@DeleteMapping("/plan/delete/{id}")
	public String deactivatePlan(@PathVariable("id") int planId) {
		try {
			InsurancePlan planObj = insurancePlanService.checkForHealthPlan(planId);
			if (planObj != null) {
				int result = insurancePlanService.deactivatePlan(Constants.DEACTIVE, planId);
				if (result > 0) {
					return "Health Plan Deleted Successfully";
				} else {
					return "Try Again";
				}
			} else {
				return "Health plan you are trying to delete does not exists";
			}
		} catch (Exception e) {
			return "Health plan you are trying to delete does not exists";
		}
	}
}
