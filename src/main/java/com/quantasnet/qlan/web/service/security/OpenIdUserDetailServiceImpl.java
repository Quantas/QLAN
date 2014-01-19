package com.quantasnet.qlan.web.service.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

import com.quantasnet.qlan.web.domain.User;
import com.quantasnet.qlan.web.service.UserService;

@Service
public class OpenIdUserDetailServiceImpl  implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserDetails(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        return load(token);
    }

	private User load(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		
		final User user = userService.getUserByUsername(token.getIdentityUrl());

		if (null == user) {
			final User newUser = new User();
			newUser.setActive(true);
			newUser.setUserName(token.getIdentityUrl());
			newUser.setFirstName("Steam");
			newUser.setLastName("User");
			newUser.setEmail("steam_user@localhost.net");
			newUser.setOpenId(true);
			newUser.setPassword(UUID.randomUUID().toString());
			return userService.saveOpenIdUser(newUser);
		}

		return user;
	}
}
