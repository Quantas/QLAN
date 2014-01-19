package com.quantasnet.qlan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "server")
public class Server extends QlanDomainBase {

	private static final long serialVersionUID = 8984439843840588020L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "steam")
	private Boolean steam;
	
	@Column(name = "game")
	private String game;

	@Column(name = "hostname")
	private String hostname;
	
	@Column(name = "port")
	private Integer port;
	
	@Column(name = "max_players")
	private Integer maxPlayers;
	
	@Column(name = "cur_players")
	private Integer currentPlayers;
	
	@Column(name = "ping")
	private Integer ping;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSteam() {
		return steam;
	}

	public void setSteam(Boolean steam) {
		this.steam = steam;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Integer getCurrentPlayers() {
		return currentPlayers;
	}

	public void setCurrentPlayers(Integer currentPlayers) {
		this.currentPlayers = currentPlayers;
	}

	public Integer getPing() {
		return ping;
	}

	public void setPing(Integer ping) {
		this.ping = ping;
	}
}
