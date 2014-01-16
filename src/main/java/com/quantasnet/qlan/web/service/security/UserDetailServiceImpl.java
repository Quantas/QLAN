package com.quantasnet.qlan.web.service.security;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quantasnet.qlan.web.domain.User;
import com.quantasnet.qlan.web.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Resource
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		return load(username);
	}

	private User load(final String userId) throws UsernameNotFoundException,
			DataAccessException {
		final User user = userService.getUserByUsername(userId);

		if (null == user) {
			throw new UsernameNotFoundException("The user does not exist");
		}

		return user;
	}
}