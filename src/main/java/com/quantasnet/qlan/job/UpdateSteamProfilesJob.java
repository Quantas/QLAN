package com.quantasnet.qlan.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quantasnet.qlan.components.SteamAPI;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.service.UserService;
import com.quantasnet.qlan.steam.api.SteamProfile;

@Component
public class UpdateSteamProfilesJob {
	
	private static final Logger LOG = LoggerFactory.getLogger(UpdateSteamProfilesJob.class);
	
	private final UserService userService;
	private final SteamAPI steamAPI;
	
	@Autowired
	public UpdateSteamProfilesJob(final UserService userService, final SteamAPI steamAPI) {
		this.userService = userService;
		this.steamAPI = steamAPI;
	}
	
	/*
	 * Every 5 Minutes
	 */
	@Scheduled(fixedDelay = 300000)
	public void updateProfiles() {
		final List<User> users = userService.getAllSteamUsers();
		
		for (final User user : users) {
			LOG.debug("Updating Steam Profile For - {}", user.getSteamId());
			final SteamProfile profile = steamAPI.getProfileForId(user.getSteamId());
			LOG.debug("Found Steam Profile  = {}", profile);
			
			user.setSteamGame(profile.getGameName());
			user.setSteamOnline(profile.getOnlineState() == 0 ? false : true);
			user.setUserName(profile.getNickname());
			user.setImageUrl(profile.getImageUrl());
			userService.update(user);
		}
	}
}
