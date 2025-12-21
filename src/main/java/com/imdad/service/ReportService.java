package com.imdad.service;

import java.util.List;

import com.imdad.dto.SearchRequest;
import com.imdad.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportPdf(HttpServletResponse response)throws Exception;
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
}
