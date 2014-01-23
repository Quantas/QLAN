package com.quantasnet.qlan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.repo.LanRepository;
import com.quantasnet.qlan.repo.ServerRepository;

@Transactional
@Service
public class ServerServiceImpl implements ServerService {

	private final LanRepository lanRepository;
	private final ServerRepository serverRepository;
	
	@Autowired
	public ServerServiceImpl(final LanRepository lanRepository, final ServerRepository serverRepository) {
		this.lanRepository = lanRepository;
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
	public List<Server> findServersByLanId(long id) {
		return serverRepository.findServersByLanId(id);
	}
	
	@Override
	public void addNewServerToLan(final long id, final Server server) {
		final Lan lan = lanRepository.findOne(id);
		final Server newServer = serverRepository.saveAndFlush(server);
		lan.getServers().add(newServer);
		lanRepository.saveAndFlush(lan);
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
