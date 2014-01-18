package com.quantasnet.qlan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quantasnet.qlan.web.domain.Lan;
import com.quantasnet.qlan.web.domain.Tournament;
import com.quantasnet.qlan.web.repo.LanRepository;
import com.quantasnet.qlan.web.repo.TournamentRepository;

@RequestMapping("/admin/setup")
@Controller
public class LanSetupController {

	private final LanRepository lanRepository;
	private final TournamentRepository tournamentRepository;
	
	@Autowired
	public LanSetupController(final LanRepository lanRepository, final TournamentRepository tournamentRepository) {
		this.lanRepository = lanRepository;
		this.tournamentRepository = tournamentRepository;
	}
	
	@RequestMapping("/lans")
	public String lans(final Model model) {
		model.addAttribute("lans", lanRepository.findAll());
		return "admin/setup/lans";
	}
	
	@RequestMapping("/lan/{id}")
	public String lan(@PathVariable final long id, final Model model) {
		model.addAttribute("lan", lanRepository.findOne(id));
		return "admin/setup/lan";
	}
	
	@RequestMapping("/lan/update")
	public String update(final Lan lan) {
		lanRepository.saveAndFlush(lan);
		return "redirect:/admin/setup/lans";
	}
	
	@RequestMapping("/newlan")
	public String newLan(final Model model) {
		model.addAttribute("lan", new Lan());
		return "admin/setup/newlan";
	}
	
	@RequestMapping(value = "/newlan/save", method = RequestMethod.POST)
	public String saveLan(final Lan lan, final Model model) {
		
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/admin/setup/lans";
	}
	
	// EVENTS
	
	@RequestMapping("/lan/tournament/add/{id}")
	public String addTournament(@PathVariable final long id) {
		final Lan lan = lanRepository.findOne(id);
		
		if (null == lan) {
			return "redirect:/lan/" + id;
		}
		
		final Tournament event = new Tournament();
		event.setName("TEST");
		final Tournament newEvent = tournamentRepository.saveAndFlush(event);
		
		lan.getTournaments().add(newEvent);
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/lan/" + id;
	}
}
