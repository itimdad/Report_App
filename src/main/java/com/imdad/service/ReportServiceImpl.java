package com.imdad.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.imdad.dto.SearchRequest;
import com.imdad.entity.CitizenPlan;
import com.imdad.repository.CitizenPlanRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		 /*
         * Workbook represents the entire Excel file.
         * XSSFWorkbook is used for .xlsx format.
         */
		Workbook workbook = new XSSFWorkbook();
		
        /*
         * Sheet represents one sheet inside Excel file.
         * Sheet name will be visible at bottom of Excel.
         */
		Sheet sheet = workbook.createSheet("Citizen plans");
		
		Row headRow = sheet.createRow(0);
		
		headRow.createCell(0).setCellValue("ID");
		headRow.createCell(1).setCellValue("Citizen Name");
		headRow.createCell(2).setCellValue("Gender");
		headRow.createCell(3).setCellValue("Plan Name");
		headRow.createCell(4).setCellValue("Plan Status");
		headRow.createCell(5).setCellValue("Start Date");
		headRow.createCell(6).setCellValue("End Date");
		headRow.createCell(7).setCellValue("Benefit Amount");
		
		List<CitizenPlan> plans = repo.findAll();
		
		int index = 1;
		for(CitizenPlan plan : plans) {
			Row dataRow = sheet.createRow(index);
			
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			
			//checking start Date is null if yes then print NA if not null print Date
			if(plan.getPlanStartDate() != null) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			
			//checking end Date is null if yes then print NA if not null print Date
			if(plan.getPlanEndDate() != null) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			
			if(plan.getBenefitAmount() != null) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			} else {
				dataRow.createCell(7).setCellValue("NA");
			}
			
			index++;
		}
		
		
        /*
         * Write the Excel file data into HTTP response output stream.
         * This sends the Excel file to the browser.
         */
		ServletOutputStream outputStream = response.getOutputStream();
		
		workbook.write(outputStream);
		
		workbook.close();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) {

		return false;
	}




}
