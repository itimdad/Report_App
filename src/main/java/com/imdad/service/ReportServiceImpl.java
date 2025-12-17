package com.imdad.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.imdad.dto.SearchRequest;
import com.imdad.entity.CitizenPlan;
import com.imdad.repository.CitizenPlanRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	CitizenPlanRepository repo;

	@Override
	public List<String> getPlanNames() {

		List<String> planNames = repo.getPlanNames();

		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {

		List<String> planStatuses = repo.getPlanStatus();

		return planStatuses;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan citizenPlan  = new CitizenPlan();
		
		if(request.getPlanName() != null && ! request.getPlanName().equals("")) {
			citizenPlan.setPlanName(request.getPlanName());
		}
		
		if(request.getPlanStatus() != null && ! request.getPlanStatus().equals("")) {
			citizenPlan.setPlanStatus(request.getPlanStatus());
		}		
		
		if(request.getGender() != null && !request.getGender().equals("")) {
			citizenPlan.setGender(request.getGender());
		}
		
		if(request.getStartDate() != null) {
			citizenPlan.setPlanStartDate(request.getStartDate());
		}

		if(request.getEndDate() != null) {
			citizenPlan.setPlanStartDate(request.getStartDate());
		}		
		
		
		List<CitizenPlan> all = repo.findAll(Example.of(citizenPlan));

		return all;
	}

	@Override
	public boolean exportPdf() {

		return false;
	}

	@Override
	public boolean exportExcel() {

		return false;
	}

}
