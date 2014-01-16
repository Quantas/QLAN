package com.quantasnet.qlan.web.components;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.quantasnet.qlan.web.domain.Role;
import com.quantasnet.qlan.web.domain.User;

@Component
public class UserFactory {
	
	@Resource
	private PasswordEncoder passwordEncoder;

	public User make(final String userName, final String firstName,
			final String lastName, final String email, final String password,
			final Set<Role> roles) {
		final User user = new User();
		user.setActive(true);
		user.setRoles(roles);
		user.setEmail(email);
		user.setGravatarHash(generateGravatarHash(email));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(encodePassword(password));

		return user;
	}

	public User make(final User user) {
		user.setActive(true);
		user.setPassword(encodePassword(user.getPassword()));
		user.setGravatarHash(generateGravatarHash(user.getEmail()));

		return user;
	}

	public User changePassword(final User user, final String newPassword) {
		user.setPassword(encodePassword(newPassword));
		return user;
	}

	private String encodePassword(final String password) {
		return passwordEncoder.encode(password);
	}

	private String generateGravatarHash(final String email) {
		return DigestUtils.md5Hex(email.trim().toLowerCase());
	}
}
