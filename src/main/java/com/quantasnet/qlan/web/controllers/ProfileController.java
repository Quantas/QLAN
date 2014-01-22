package com.quantasnet.qlan.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.service.UserService;

@SessionAttributes({ ProfileController.PROFILE_USER })
@RequestMapping("/profile")
@Controller
public class ProfileController {

	public static final String PROFILE_USER = "profileUser";
	
	private final UserService userService;
	
	@Autowired
	public ProfileController(final UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping
	public String profile(@AuthenticationPrincipal final User user, final Model model) {
		final User profileUser = userService.getUserById(user.getId());
		model.addAttribute(PROFILE_USER, profileUser);
		return "profile";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProfile(@AuthenticationPrincipal final User user, @Valid @ModelAttribute(PROFILE_USER) final User profileUser, final BindingResult bindingResult, final Model model) {
		if (user.getId().equals(profileUser.getId())) {
			final User emailCheck = userService.getUserByEmail(profileUser.getEmail());
			
			if (null != emailCheck && !emailCheck.getId().equals(user.getId())) {
				bindingResult.addError(new FieldError("profileUser", "email", "Email already taken"));
			}
			
			if (bindingResult.hasErrors()) {
				return "profile";
			} else {
				userService.profileUpdate(profileUser);
			}
			
		}
		return "redirect:/profile";
	}
}
