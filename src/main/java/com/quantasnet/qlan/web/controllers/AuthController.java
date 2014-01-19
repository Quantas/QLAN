package com.quantasnet.qlan.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author andrewlandsverk
 */
@Controller
public class AuthController {
	
	@RequestMapping("/login")
	public String login() {
		return "auth/login";
	}

	@RequestMapping("/denied")
	public String denied() {
		return "auth/denied";
	}
}