package com.quantasnet.qlan.web.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quantasnet.qlan.web.components.SteamAPI;
import com.quantasnet.qlan.web.domain.User;
import com.quantasnet.qlan.web.service.UserService;

@Component
public class UpdateSteamProfilesJob {
	
	private static final Logger LOG = LoggerFactory.getLogger(UpdateSteamProfilesJob.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SteamAPI steamAPI;
	
	/*
	 * Every 5 Minutes
	 */
	@Scheduled(fixedDelay = 300000)
	public void updateProfiles() {
		final List<User> users = userService.getAllUsers();
		
		for (final User user : users) {
			if (user.isSteam()) {
				LOG.debug("Updating Steam Profile For - {}", user.getSteamId());
				final SteamAPI.Profile profile = steamAPI.getProfileForId(user.getSteamId());
				LOG.debug("Found Steam Profile  = {}", profile);
				
				user.setSteamGame(profile.getGameName());
				user.setSteamOnline(profile.getOnlineState() == 0 ? false : true);
				user.setUserName(profile.getNickname());
				user.setImageUrl(profile.getImageUrl());
				userService.update(user);
			}
		}
	}
}
