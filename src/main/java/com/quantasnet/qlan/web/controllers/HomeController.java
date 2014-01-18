package com.quantasnet.qlan.web.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quantasnet.qlan.web.domain.Lan;
import com.quantasnet.qlan.web.service.LanService;

@Controller
public class HomeController {

	private final LanService lanService;
	
	@Autowired
	public HomeController(final LanService lanService) {
		this.lanService = lanService;
	}
	
	@ModelAttribute("currentLans")
	public Set<Lan> currentLan() {
		return lanService.getActiveLans();
	}
	
	@ModelAttribute("futureLans")
	public Set<Lan> futureLans() {
		return lanService.getFutureLans();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(final Model model) {
		return "home";
	}
}