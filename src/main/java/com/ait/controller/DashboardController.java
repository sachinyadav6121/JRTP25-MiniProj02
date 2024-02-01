package com.ait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ait.service.IDashboardService;

@Controller
public class DashboardController {

	@Autowired
	private IDashboardService dService;
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		String quoteText = dService.getQuote();
		model.addAttribute("quote",quoteText);
		return "dashboard";
	}
	

}
