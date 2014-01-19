package com.quantasnet.qlan.web.controllers;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.repo.LanRepository;
import com.quantasnet.qlan.repo.ServerRepository;

@Controller
@RequestMapping("/lan")
public class LanController {

	private final LanRepository lanRepository;
	private final ServerRepository serverRepository;
	
	@Autowired
	public LanController(final LanRepository lanRepository, final ServerRepository serverRepository) {
		this.lanRepository = lanRepository;
		this.serverRepository = serverRepository;
	}
	
	@RequestMapping("/{id}")
	public String view(@PathVariable final long id, @AuthenticationPrincipal final User user, final Model model) {
		final Lan lan = lanRepository.findOne(id);
		
		if (null == lan) {
			return "forward:/404";
		}
		
		boolean attending = Boolean.FALSE;
		
		for (final User attendee : lan.getUsers()) {
			if (attendee.getId().equals(user.getId())) {
				attending = Boolean.TRUE;
				break;
			}
		}
		
		final DateTime now = DateTime.now();
		final long current = now.getMillis();
		final long start = lan.getStart().getMillis();
		final long end = lan.getEnd().getMillis();
		
		final double percent = (double)(end - current) / (end - start) * 100.0;
		
		model.addAttribute("now", now);
		model.addAttribute("percentLeft", percent);
		model.addAttribute("attending", attending);
		model.addAttribute("lan", lan);
		return "lan";
	}
	
	@RequestMapping("/join/{id}")
	public String join(@PathVariable final long id, @AuthenticationPrincipal final User user) {
		final Lan lan = lanRepository.findOne(id);
		
		if (null == lan) {
			return "forward:/404";
		}
		
		for (final User attendee : lan.getUsers()) {
			if (attendee.getId().equals(user.getId())) {
				return "redirect:/lan/" + id;
			}
		}
		
		lan.getUsers().add(user);
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/lan/" + id;
	}
	
	@RequestMapping("/leave/{id}")
	public String leave(@PathVariable final long id, @AuthenticationPrincipal final User user) {
		final Lan lan = lanRepository.findOne(id);
		
		if (null == lan) {
			return "forward:/404";
		}
		
		User toDelete = null;
		
		for (final User attendee : lan.getUsers()) {
			if (attendee.getId().equals(user.getId())) {
				toDelete = attendee;
			}
		}
		
		lan.getUsers().remove(toDelete);
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/lan/" + id;
	}
	
	@RequestMapping("/server/add/{id}")
	public String addServer(@PathVariable final long id) {
		final Lan lan = lanRepository.findOne(id);
		
		if (null == lan) {
			return "forward:/404";
		}
		
		final Server server = new Server();
		server.setGame("TF2");
		server.setHostname("shark1");
		server.setPort(27015);
		
		final Server newServer = serverRepository.saveAndFlush(server);
		
		lan.getServers().add(newServer);
		lanRepository.saveAndFlush(lan);
		
		return "redirect:/lan/" + id;
	}
}
