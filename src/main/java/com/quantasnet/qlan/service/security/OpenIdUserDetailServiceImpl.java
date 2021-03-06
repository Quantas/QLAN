package com.quantasnet.qlan.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

import com.quantasnet.qlan.components.SteamAPI;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.service.UserService;
import com.quantasnet.qlan.steam.api.SteamProfile;

@Service
public class OpenIdUserDetailServiceImpl  implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    
	private static final String STEAM_OPENID_URL = "http://steamcommunity.com/openid/id/";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SteamAPI steamAPI;
	
	@Override
	public UserDetails loadUserDetails(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        return load(token);
    }

	private User load(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		final String url = token.getIdentityUrl();
		if (url.startsWith(STEAM_OPENID_URL)) {
			final long id = Long.parseLong(url.substring(url.lastIndexOf('/') + 1));
			final SteamProfile profile = steamAPI.getProfileForId(id);
			
			// check for existing user
			final User user = userService.getUserBySteamId(id);
			
			if (null == user) {
				// make new user
				return userService.saveOpenIdUser(profile);
			}
	
			return user;
		}
		throw new UsernameNotFoundException("Invalid OpenID Provider!");
	}
}
