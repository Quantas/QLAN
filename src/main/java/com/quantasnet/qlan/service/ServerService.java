package com.quantasnet.qlan.service;

import java.util.List;

import com.quantasnet.qlan.domain.Server;

public interface ServerService {
	List<Server> findAllSteamServers();
	Server updateServer(Server server);
}
