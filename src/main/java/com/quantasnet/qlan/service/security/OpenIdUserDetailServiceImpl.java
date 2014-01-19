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
    
	private final UserService userService;
	private final SteamAPI steamAPI;
	
	@Autowired
	public OpenIdUserDetailServiceImpl(final UserService userService, final SteamAPI steamAPI) {
		this.userService = userService;
		this.steamAPI = steamAPI;
	}

	@Override
	public UserDetails loadUserDetails(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        return load(token);
    }

	private User load(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		final String url = token.getIdentityUrl();
		final long id = Long.parseLong(url.substring(url.lastIndexOf('/') + 1));
		final SteamProfile profile = steamAPI.getProfileForId(id);
		
		final User user = userService.getUserBySteamId(id);
		
		if (null == user) {
			return userService.saveOpenIdUser(profile);
		}

		return user;
	}
}