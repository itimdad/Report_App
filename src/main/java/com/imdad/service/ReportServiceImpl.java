package com.imdad.service;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.imdad.dto.SearchRequest;
import com.imdad.entity.CitizenPlan;
import com.imdad.repository.CitizenPlanRepository;
import com.imdad.utility.EmailUtils;
import com.imdad.utility.ExcelGenerator;
import com.imdad.utility.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	CitizenPlanRepository repo;
	
	@Autowired
	PdfGenerator pdfGenerator;

	@Autowired
	ExcelGenerator excelGenerator;
	
	@Autowired
	EmailUtils emailUtils;
	
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
			citizenPlan.setPlanStartDate(request.getEndDate());
		}		
		
		
		List<CitizenPlan> all = repo.findAll(Example.of(citizenPlan));

		return all;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		List<CitizenPlan> plans = repo.findAll();
		
		File f = new File("Plans.pdf");
		
		String subject = "Test mail subject";
		String body = "<h1> Test mail body </h1>";
		String to = "iamfirdausi01@gmail.com";
		
		
		pdfGenerator.generatePdf(response, plans, f);
		
		emailUtils.sendMail(to, subject, body, f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {

		List<CitizenPlan> plans = repo.findAll();
		
		File f = new File("Plans.xlsx");
		
		String subject = "Test mail subject";
		String body = "<h1> Test mail body </h1>";
		String to = "iamfirdausi01@gmail.com";
		
		excelGenerator.generateExcel(response, plans, f);
		
		emailUtils.sendMail(to, subject, body, f);
		
		f.delete();
		return true;
	}
	

	
}
