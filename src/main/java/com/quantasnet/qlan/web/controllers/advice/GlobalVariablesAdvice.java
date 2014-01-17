package com.quantasnet.qlan.web.controllers.advice;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.quantasnet.qlan.web.controllers.editors.DateTimeEditor;
import com.quantasnet.qlan.web.domain.Lan;
import com.quantasnet.qlan.web.service.LanService;

@ControllerAdvice(annotations = Controller.class)
public class GlobalVariablesAdvice {
	
	private final LanService lanService;
	
	@Autowired
	public GlobalVariablesAdvice(final LanService lanService) {
		this.lanService = lanService;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(DateTime.class, new DateTimeEditor());
	}
	
	@ModelAttribute("currentLan")
	public Lan currentLan() {
		return lanService.getActiveLan();
	}
	
	@ModelAttribute("futureLans")
	public Set<Lan> futureLans() {
		return lanService.getFutureLans();
	}
}