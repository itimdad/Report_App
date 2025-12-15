	package com.imdad.controller;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.imdad.entity.CitizenPlan;
import com.imdad.entity.SearchRequest;
	import com.imdad.service.ReportServiceImpl;
	import org.springframework.web.bind.annotation.RequestParam;
	
	
	@Controller
	public class ReportController {
	
		
		@Autowired
		ReportServiceImpl reportServiceImpl;
	
	
	
		@GetMapping("/index")
		public String indexPage(Model model) {
			
			List<String> planStatus = reportServiceImpl.getPlanStatus();
			List<String> planNames = reportServiceImpl.getPlanNames();
			
			model.addAttribute("searchRequest", new SearchRequest());
			model.addAttribute("plans", planNames);
			model.addAttribute("status", planStatus);
			
			return "index";
		}
		
		@PostMapping("/search")
		public String searchData(@ModelAttribute SearchRequest request) {
			
			System.out.println(request.getPlanName());
			System.out.println(request.getPlanStatus());
			System.out.println(request.getGender());
			System.out.println(request.getStartDate());
			System.out.println(request.getEndDate());
			
			List<CitizenPlan> searchAll = reportServiceImpl.searchAll();
			System.out.println(searchAll);
			
			return "success";
		}
		
	
	}
