package com.quantasnet.qlan.service;

import java.util.List;

import com.quantasnet.qlan.domain.Server;

public interface ServerService {
	Server findById(long id);
	List<Server> findAllSteamServers();
	List<Server> findServersByLanId(long id);
	Server updateServer(Server server);
	void removeServer(Server server);
}
