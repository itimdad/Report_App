package com.imdad.service;

import java.util.List;

import com.imdad.entity.CitizenPlan;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> searchAll();
	
	public boolean exportPdf();
	
	public boolean exportExcel();
}
