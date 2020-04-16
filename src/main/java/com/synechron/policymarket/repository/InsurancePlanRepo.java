package com.synechron.policymarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synechron.policymarket.model.InsurancePlan;

@Repository
public interface InsurancePlanRepo extends JpaRepository<InsurancePlan, Integer> {

	List<InsurancePlan> findByIsActive(int isActive);

	List<InsurancePlan> findByAgeAndCity(int age, String city);

	@Modifying
	@Query("update InsurancePlan ip set ip.isActive=:status where ip.planId=:planId")
	int deactivatePlan(@Param("status") int status, @Param("planId") int planId);

}
