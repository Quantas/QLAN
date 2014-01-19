package com.quantasnet.qlan.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quantasnet.qlan.components.SteamAPI;
import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.service.ServerService;
import com.quantasnet.qlan.steam.api.SteamServer;

@Component
public class UpdateSteamServersJob {

	private static final Logger LOG = LoggerFactory.getLogger(UpdateSteamServersJob.class);
	
	private final ServerService serverService;
	private final SteamAPI steamAPI;
	
	@Autowired
	public UpdateSteamServersJob(final ServerService serverService, final SteamAPI steamAPI) {
		this.serverService = serverService;
		this.steamAPI = steamAPI;
	}
	
	@Scheduled(fixedDelay = 30000)
	public void updateServers() {
		final List<Server> servers = serverService.findAllSteamServers();
		for (final Server server : servers) {
			LOG.info("Querying Server {}", server.getId());
			final SteamServer steamServer = steamAPI.getSourceServer(server);
			if (null != steamServer) {
				server.setCurrentPlayers(steamServer.getCurrentPlayers());
				server.setMaxPlayers(steamServer.getMaxPlayers());
				server.setPing(steamServer.getPing());
				server.setGame(steamServer.getName());
				serverService.updateServer(server);
				LOG.info("Server {} Successfully Updated = {}", server.getId(), server);
			}
		}
	}
}
