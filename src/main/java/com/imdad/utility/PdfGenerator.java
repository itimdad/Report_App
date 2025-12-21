package com.imdad.utility;

import java.util.List;


import org.springframework.stereotype.Component;

import com.imdad.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public  boolean genratePdf(HttpServletResponse response, List<CitizenPlan> plans) throws Exception {

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Paragraph p = new Paragraph("CItizen plans info");

		document.add(p);

		PdfPTable table = new PdfPTable(8);

		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Geneder");
		table.addCell("Plane Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");

		for (CitizenPlan plan : plans) {

			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if(plan.getPlanStartDate() != null) {

				table.addCell(plan.getPlanStartDate() + "");
			} 
			else {
				table.addCell("NA");
			}
			
			if(plan.getPlanEndDate() != null) {
				
				table.addCell(plan.getPlanEndDate() + "");
			}
			else {
				table.addCell("NA");
			}
			
			if(plan.getBenefitAmount() != null) {
				
				table.addCell(plan.getBenefitAmount() + "");
			}
			else {
				table.addCell("NA");
			}

		}

		document.add(table);

		document.close();

		return true;
	}

}
