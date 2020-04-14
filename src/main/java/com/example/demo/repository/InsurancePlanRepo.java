package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.InsurancePlan;

public interface InsurancePlanRepo extends JpaRepository<InsurancePlan, Integer> {

	List<InsurancePlan> findByIsActive(int isActive);

	@Modifying
	@Query("update InsurancePlan ip set ip.isActive=:status where ip.planId=:planId")
	int deactivatePlan(@Param("status") int status, @Param("planId") int planId);

}
