package com.quantasnet.qlan.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserService userService;
	
	@Autowired
	public UserDetailServiceImpl(final UserService userService) {
		this.userService = userService;
	}

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