package com.quantasnet.qlan.web.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SteamAPI {
	
	private static final String API_KEY = "CDF6D9864BD56E1E2C6701E98D5EED51";
	private static final String URL_PREFIX = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=";
	
	public Profile getProfileForId(final long id) {
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
				final Profile profile = new Profile();
				
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
	
	public static class Profile {
	    private String imageUrl;
	    private int onlineState;
	    private String realName;
	    private String nickname;
	    private long steamId64;
	    private String gameName;
	    
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public int getOnlineState() {
			return onlineState;
		}
		public void setOnlineState(int onlineState) {
			this.onlineState = onlineState;
		}
		public String getRealName() {
			return realName;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public long getSteamId64() {
			return steamId64;
		}
		public void setSteamId64(long steamId64) {
			this.steamId64 = steamId64;
		}
		public String getGameName() {
			return gameName;
		}
		public void setGameName(String gameName) {
			this.gameName = gameName;
		}
	}
}
