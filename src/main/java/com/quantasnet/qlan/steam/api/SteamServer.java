package com.quantasnet.qlan.steam.api;

import com.quantasnet.qlan.domain.QlanDomainBase;

public class SteamServer extends QlanDomainBase {

	private static final long serialVersionUID = -6870378099792747423L;

	private int maxPlayers;
	private int currentPlayers;
	private int ping;
	private String name;
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public int getCurrentPlayers() {
		return currentPlayers;
	}
	public void setCurrentPlayers(int currentPlayers) {
		this.currentPlayers = currentPlayers;
	}
	public int getPing() {
		return ping;
	}
	public void setPing(int ping) {
		this.ping = ping;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}