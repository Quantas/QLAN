package com.quantasnet.qlan.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.domain.Tournament;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.model.ModelConstants;
import com.quantasnet.qlan.service.LanService;
import com.quantasnet.qlan.service.ServerService;
import com.quantasnet.qlan.service.UserService;

@Controller
@RequestMapping("/lan")
public class LanController {

	private final LanService lanService;
	private final ServerService serverService;
	private final UserService userService;
	
	@Autowired
	public LanController(final LanService lanService, final ServerService serverService, final UserService userService) {
		this.lanService = lanService;
		this.serverService = serverService;
		this.userService = userService;
	}
	
	@RequestMapping("/{id}")
	public String view(@PathVariable final long id, @AuthenticationPrincipal final User user, final Model model) {
		final Lan lan = lanService.findOne(id);
		model.addAttribute("lan", lan);
		model.addAttribute("newServer", new Server());
		model.addAttribute("newTournament", new Tournament());
		return "lan";
	}
	
	@RequestMapping(value = "/{id}/servers", method = RequestMethod.GET)
	public String loadServers(@PathVariable final long id, final Model model) {
		final List<Server> servers = serverService.findServersByLanId(id);
		model.addAttribute("servers", servers);
		model.addAttribute("lanId", id);
		return "lan/servers";
	}
	
	@RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
	public String loadUsers(@PathVariable final long id, @AuthenticationPrincipal final User user, final Model model) {
		final List<User> users = userService.findUsersByLanId(id);
		
		boolean attending = Boolean.FALSE;
		
		for (final User attendee : users) {
			if (attendee.getId().equals(user.getId())) {
				attending = Boolean.TRUE;
				break;
			}
		}
		
		model.addAttribute("attending", attending);
		model.addAttribute("users", users);
		model.addAttribute("lanId", id);
		return "lan/users";
	}
	
	@RequestMapping(value = "/{id}/timeLeft", method = RequestMethod.GET)
	public String loadTimeLeft(@PathVariable final long id, final Model model) {
		final Lan lan = lanService.findOne(id);
		
		final long current = DateTime.now().getMillis();
		final long start = lan.getStart().getMillis();
		final long end = lan.getEnd().getMillis();
		
		final double percent = (double)(end - current) / (end - start) * 100.0;
		
		model.addAttribute("percentLeft", percent);
		
		return "lan/timeLeft";
	}
	
	@RequestMapping("/join/{id}")
	public String join(@PathVariable final long id, @AuthenticationPrincipal final User user) {
		lanService.joinLan(user, id);
		return "redirect:/lan/" + id;
	}
	
	@RequestMapping("/leave/{id}")
	public String leave(@PathVariable final long id, @AuthenticationPrincipal final User user) {
		lanService.leaveLan(user, id);
		return "redirect:/lan/" + id;
	}
	
	@RequestMapping(value = "/server/add/{id}", method = RequestMethod.POST)
	public String addServer(@Valid final Server server, final BindingResult result,  @PathVariable final long id, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			
			final StringBuilder builder = new StringBuilder();
			for (final FieldError error : result.getFieldErrors()) {
				builder.append(error.getDefaultMessage())
				.append("<br />");
			}
			
			redirectAttributes.addFlashAttribute(ModelConstants.FAILURE_STATUS, "There was an error saving the new server:<br /><br />" + builder.toString());
			return "redirect:/lan/" + id;
		}
		
		serverService.addNewServerToLan(id, server);
		return "redirect:/lan/" + id;
	}
}
