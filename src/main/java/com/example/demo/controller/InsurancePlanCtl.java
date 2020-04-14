package com.example.demo.controller;

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

import com.example.demo.model.InsurancePlan;
import com.example.demo.service.InsurancePlanService;

@RestController
@RequestMapping(path = "policymarket")
public class InsurancePlanCtl {

	@Autowired
	private InsurancePlanService insurancePlanService;

	@GetMapping("/plan/getAll")
	public List<InsurancePlan> getAllActivePlans() {
		return insurancePlanService.getAllPlans(1);
	}

	@DeleteMapping("/plan/delete/{id}")
	public String deactivatePlan(@PathVariable("id") int planId) {
		try {
			InsurancePlan planObj = insurancePlanService.checkForPlan(planId);
			System.out.println("---Plan Obj=" + planObj);
			if (planObj != null) {
				int result = insurancePlanService.deactivatePlan(0, planId);
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

	@PostMapping("/plan/add")
	public String addPolicyPlan(@RequestBody InsurancePlan insurancePlan) {
		InsurancePlan result = insurancePlanService.addPolicyPlans(insurancePlan);
		if (result.getPlanId() != 0) {
			return "Health Plan Registered Successfully";
		} else {
			return "Try Again";
		}
	}

	@PutMapping("/plan/update")
	public String updatePolicyPlan(@RequestBody InsurancePlan insurancePlan) {
		try {
			InsurancePlan plan = insurancePlanService.checkForPlan(insurancePlan.getPlanId());
			if (plan != null) {
				InsurancePlan result = insurancePlanService.updatePolicyPlans(insurancePlan);
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
}
