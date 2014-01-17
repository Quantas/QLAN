package com.quantasnet.qlan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quantasnet.qlan.web.domain.Lan;
import com.quantasnet.qlan.web.repo.LanRepository;

@RequestMapping("/admin/setup")
@Controller
public class SetupController {

	@Autowired
	private LanRepository lanRepository;
	
	@RequestMapping("/newlan")
	public String newLan(final Model model) {
		model.addAttribute("lan", new Lan());
		return "admin/setup/newlan";
	}
	
	@RequestMapping(value = "/newlan/save", method = RequestMethod.POST)
	public String saveLan(final Lan lan, final Model model) {
		
		lanRepository.save(lan);
		
		return "redirect:/";
	}
}
