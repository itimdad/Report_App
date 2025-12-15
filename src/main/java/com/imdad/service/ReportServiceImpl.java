package com.imdad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<CitizenPlan> searchAll() {

		List<CitizenPlan> all = repo.findAll();

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
