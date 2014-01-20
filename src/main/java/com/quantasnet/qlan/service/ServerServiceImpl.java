package com.quantasnet.qlan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.repo.ServerRepository;

@Transactional
@Service
public class ServerServiceImpl implements ServerService {

	private final ServerRepository serverRepository;
	
	@Autowired
	public ServerServiceImpl(final ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}
	
	@Override
	public Server findById(long id) {
		return serverRepository.findOne(id);
	}
	
	@Override
	public List<Server> findAllSteamServers() {
		return serverRepository.findBySteam(Boolean.TRUE);
	}
	
	@Override
	public Server updateServer(final Server server) {
		return serverRepository.saveAndFlush(server);
	}
	
	@Override
	public void removeServer(final Server server) {
		serverRepository.delete(server);
	}
}
