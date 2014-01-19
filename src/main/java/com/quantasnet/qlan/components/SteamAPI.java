package com.quantasnet.qlan.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import com.quantasnet.qlan.domain.Server;
import com.quantasnet.qlan.steam.api.SteamProfile;
import com.quantasnet.qlan.steam.api.SteamServer;

@Component
public class SteamAPI {
	private static final Logger LOG = LoggerFactory.getLogger(SteamAPI.class);
	
	private static final String API_KEY = "CDF6D9864BD56E1E2C6701E98D5EED51";
	private static final String URL_PREFIX = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=";
	
	public SteamServer getSourceServer(final Server server) {
		try {
			final SourceServer sourceServer = new SourceServer(server.getHostname(), server.getPort());
			
			final Map<String, Object> serverInfo = sourceServer.getServerInfo();
			final int ping = sourceServer.getPing();

			final SteamServer steamServer = new SteamServer();
			steamServer.setPing(ping);
			steamServer.setCurrentPlayers((byte) serverInfo.get("numberOfPlayers"));
			steamServer.setMaxPlayers((byte) serverInfo.get("maxPlayers"));
			steamServer.setName((String) serverInfo.get("serverName"));
			return steamServer;
			
		} catch (Exception e) {
			LOG.error("Error in SteamAPI", e);
			// this will fail a bunch I guess...
		}
		
		return null;
	}
	
	public SteamProfile getProfileForId(final long id) {
		try {
			final URL url = new URL(URL_PREFIX + API_KEY + "&steamids=" + id);
	        final URLConnection urlc = url.openConnection();
	        final BufferedReader bfr = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			
			final StringBuilder builder = new StringBuilder(2048);
			String line;
			while ((line = bfr.readLine()) != null) {
			    builder.append(line);
			}

			final JSONObject wrapper = new JSONObject(builder.toString());
			final JSONObject response = wrapper.getJSONObject("response");
			final JSONArray players = response.getJSONArray("players");
			final JSONObject player = players.getJSONObject(0);
			if (null != player) {
				final SteamProfile profile = new SteamProfile();
				
				try {
					profile.setImageUrl(player.getString("avatarfull"));
				} catch (JSONException e) {
					// bury it!
				}
				
				try {
					profile.setOnlineState(player.getInt("personastate"));
				} catch (JSONException e) {
					// bury it
				}
				
				try {
					profile.setNickname(player.getString("personaname"));
				} catch (JSONException e) {
					// bury it
				}
				
				try {
					profile.setRealName(player.getString("realname"));
				} catch (JSONException e) {
					// bury it
				}
				
				try {
					profile.setGameName(player.getString("gameextrainfo"));
				} catch (JSONException e) {
					// bury it
				}
				
				profile.setSteamId64(id);
				return profile;
			}
		} catch(Exception e) {
			
		} 
		return null;
	}
}
