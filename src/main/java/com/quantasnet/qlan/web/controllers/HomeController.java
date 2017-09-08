package com.quantasnet.qlan.web.controllers;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.service.LanService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	private final LanService lanService;
	
	@Autowired
	public HomeController(final LanService lanService) {
		this.lanService = lanService;
	}
	
	@ModelAttribute("currentLans")
	public Set<Lan> currentLan(@RequestHeader("x-forwarded-proto") final String protoForward, final HttpServletRequest request) {
		LOG.info("X-FORWARDED-PROTO = {} - {}", protoForward, request.getRequestURL());
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