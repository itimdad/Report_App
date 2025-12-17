package com.imdad.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.imdad.dto.SearchRequest;
import com.imdad.entity.CitizenPlan;
import com.imdad.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	ReportService service;

	@PostMapping("/search")
	public String handlesearch(@ModelAttribute("searchRequest") SearchRequest request, Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> results = service.search(request);
		
		model.addAttribute("results", results);
		
		init(model);
		
		return "index";
	}
	@GetMapping("/")
	public String indexPage(Model model) {

		model.addAttribute("searchRequest", new SearchRequest());
		init(model);

		return "index";
	}

	private void init(Model model) {
		
		model.addAttribute("plans", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus()); 
	}

}
