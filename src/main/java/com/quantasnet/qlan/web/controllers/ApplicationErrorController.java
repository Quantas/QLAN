package com.quantasnet.qlan.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.quantasnet.qlan.web.annotations.ErrorController;

@ErrorController
public class ApplicationErrorController {

	@RequestMapping("/404")
	public String error404() {
		return "error/404";
	}
	
	@RequestMapping("/500")
	public String error500() {
		return "error/error";
	}
}
