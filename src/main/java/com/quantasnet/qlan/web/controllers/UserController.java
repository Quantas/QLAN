package com.quantasnet.qlan.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantasnet.qlan.web.domain.User;
import com.quantasnet.qlan.web.service.UserService;

/**
 * Created by andrewlandsverk
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/signup")
	public String signup(final Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid final User user,
			final BindingResult bindingResult,
			@RequestParam("confirmPassword") final String confirmPassword,
			final Model model) {

		final User userNameCheck = userService.getUserByUsername(user
				.getUsername());

		if (null != userNameCheck) {
			bindingResult.addError(new FieldError("user", "userName", "already taken"));
		}

		if (!confirmPassword.equals(user.getPassword())) {
			bindingResult.addError(new FieldError("user", "password", "Confirm password must match"));
		}

		if (bindingResult.hasErrors()) {
			return "signup";
		} else {
			final User savedUser = userService.saveUser(user);
			if (null == savedUser) {
				model.addAttribute("message", "Error creating new user");
				model.addAttribute("user", user);
				return "signup";
			}
			return "success";
		}
	}
}