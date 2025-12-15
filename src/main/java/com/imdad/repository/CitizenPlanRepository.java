package com.imdad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imdad.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer>{

	@Query("SELECT DISTINCT(planName) from CitizenPlan")
	public List<String> getPlanNames();
	
	@Query("SELECT DISTINCT(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
}
