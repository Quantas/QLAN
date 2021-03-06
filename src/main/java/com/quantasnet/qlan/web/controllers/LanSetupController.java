package com.quantasnet.qlan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.domain.Tournament;
import com.quantasnet.qlan.repo.LanRepository;
import com.quantasnet.qlan.repo.TournamentRepository;
import com.quantasnet.qlan.service.ServerService;

@SessionAttributes("lan")
@RequestMapping("/admin/setup")
@Controller
public class LanSetupController {

	private final ServerService serverService;
	private final LanRepository lanRepository;
	private final TournamentRepository tournamentRepository;
	
	@Autowired
	public LanSetupController(final ServerService serverService, final LanRepository lanRepository, final TournamentRepository tournamentRepository) {
		this.serverService = serverService;
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
	public String saveLan(final Lan lan) {
		
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/admin/setup/lans";
	}
	
	@RequestMapping("/lan/server/remove/{lanId}/{serverId}")
	public String removeServer(@PathVariable final long lanId, @PathVariable final long serverId) {
		final Lan lan = lanRepository.findOne(lanId);
		
		Server toRemove = null;
		for (final Server server : lan.getServers()) {
			if (server.getId().equals(serverId)) {
				toRemove = server;
				break;
			}
		}
		
		if (null != toRemove) {
			lan.getServers().remove(toRemove);
			lanRepository.saveAndFlush(lan);
			serverService.removeServer(toRemove);
		}
		
		return "redirect:/lan/" + lanId;
	}
	
	// EVENTS
	
	@RequestMapping("/lan/tournament/add/{id}")
	public String addTournament(final Tournament tournament, @PathVariable final long id) {
		final Lan lan = lanRepository.findOne(id);
		
		final Tournament newEvent = tournamentRepository.saveAndFlush(tournament);
		
		lan.getTournaments().add(newEvent);
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/lan/" + id;
	}
	
	@RequestMapping("/lan/tournament/remove/{lanId}/{tournamentId}")
	public String removeTournament(@PathVariable final long lanId, @PathVariable final long tournamentId) {
		final Lan lan = lanRepository.findOne(lanId);
		
		Tournament toRemove = null;
		for (final Tournament tournament : lan.getTournaments()) {
			if (tournament.getId().equals(tournamentId)) {
				toRemove = tournament;
				break;
			}
		}
		
		if (null != toRemove) {
			lan.getTournaments().remove(toRemove);
			lanRepository.saveAndFlush(lan);
			tournamentRepository.delete(toRemove);
		}
		
		return "redirect:/lan/" + lanId;
	}
}
