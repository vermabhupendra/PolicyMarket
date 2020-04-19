package com.synechron.policymarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.policymarket.constants.Constants;
import com.synechron.policymarket.exceptions.HealthPlanNotFoundException;
import com.synechron.policymarket.model.InsurancePlan;
import com.synechron.policymarket.restutil.Response;
import com.synechron.policymarket.service.InsurancePlanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InsurancePlanController {

	@Autowired
	private InsurancePlanService insurancePlanService;

	/**
	 * Method to get all active health plans
	 * 
	 * @return
	 */
	@GetMapping("/plan/getAll")
	public ResponseEntity<Response> getAllActivePlans() {
		List<InsurancePlan> listOfAllActivePlans = insurancePlanService.getAllActivePlans(Constants.ACTIVE);
		if (listOfAllActivePlans.size() != 0) {
			return new ResponseEntity<Response>(new Response("All active health plan list", listOfAllActivePlans),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("No health plans found.", null), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to get Plans on the basis of Age and City
	 * 
	 * @param age
	 * @param city
	 * @return
	 */
	@PostMapping("/plan/getAllPlansByAgeAndCity")
	public ResponseEntity<Response> getPlansByAgeAndCity(@RequestParam(value = "age", required = true) int age,
			@RequestParam(value = "city", required = true) String city) {
		List<InsurancePlan> listOfPlansByAgeAndCity = insurancePlanService.getPlansByAgeAndCity(age, city);

		if (listOfPlansByAgeAndCity.size() != 0) {
			return new ResponseEntity<Response>(
					new Response("Health plan list as per age and city", listOfPlansByAgeAndCity), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("No health plans found.", null), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to add/register new health plan
	 * 
	 * @param insurancePlan
	 * @return
	 */
	@PostMapping("/plan/add")
	public ResponseEntity<Response> addHealthPlan(@Valid @RequestBody InsurancePlan insurancePlan) {
		InsurancePlan insurancePlanObj = insurancePlanService.addHealthPlan(insurancePlan);
		if (insurancePlanObj != null) {
			return new ResponseEntity<Response>(new Response("Health Plan Registered Successfully", null),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Response>(new Response("Failed, Please try again.", null),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Method to update the existing health plan
	 * 
	 * @param insurancePlan
	 * @return
	 * @throws HealthPlanNotFoundException
	 */
	@PutMapping("/plan/update")
	public ResponseEntity<Response> updateHealthPlan(@Valid @RequestBody InsurancePlan insurancePlan)
			throws HealthPlanNotFoundException {

		InsurancePlan plan = insurancePlanService.checkForHealthPlan(insurancePlan.getPlanId());
		log.debug("Plan = " + plan);

		if (plan == null) {
			throw new HealthPlanNotFoundException("Health plan does not exists.");
		}
		insurancePlanService.updateHealthPlan(insurancePlan);
		return new ResponseEntity<Response>(new Response("Health Plan Updated Successfully", null), HttpStatus.OK);
	}

	/**
	 * Method to delete(deactivate) the health plan
	 * 
	 * @param planId
	 * @return
	 * @throws HealthPlanNotFoundException
	 */
	@DeleteMapping("/plan/delete/{id}")
	public ResponseEntity<Response> deactivatePlan(@PathVariable("id") int planId) throws HealthPlanNotFoundException {
		InsurancePlan planObj = insurancePlanService.checkForHealthPlan(planId);
		if (planObj == null) {
			throw new HealthPlanNotFoundException("Health plan you are trying to delete does not exists.");
		}
		insurancePlanService.deactivatePlan(Constants.DEACTIVE, planId);
		return new ResponseEntity<Response>(new Response("Health plan deleted successfully", null), HttpStatus.OK);
	}
}
