package com.quantasnet.qlan.components;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.quantasnet.qlan.domain.Role;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.service.RoleService;
import com.quantasnet.qlan.steam.api.SteamProfile;

@Component
public class UserFactory {
	
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;

	@Autowired
	public UserFactory(final PasswordEncoder passwordEncoder, final RoleService roleService) {
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}
	
	public User make(final String userName, final String firstName,
			final String lastName, final String email, final String password,
			final Set<Role> roles) {
		final User user = new User();
		user.setActive(true);
		user.setRoles(roles);
		user.setEmail(email);
		user.setImageUrl(generateGravatarUrl(email));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(encodePassword(password));

		return user;
	}

	public User make(final User user) {
		user.setActive(true);
		user.setPassword(encodePassword(user.getPassword()));
		user.setImageUrl(generateGravatarUrl(user.getEmail()));
		addUserRole(user);
		
		return user;
	}
	
	public User makeSteamUser(final SteamProfile profile) {
		final User newUser = new User();
		newUser.setActive(true);
		newUser.setUserName(profile.getNickname());
		
		final String realName = profile.getRealName();
		if (null == realName) {
			newUser.setFirstName("Steam");
			newUser.setLastName("User");
		} else {
			final String[] name = realName.split(" ");
			if (name.length > 0) {
				newUser.setFirstName(name[0]);
			} else {
				newUser.setFirstName("Steam");
				newUser.setLastName("User");
			}
			if (name.length > 1) {
				newUser.setLastName(name[1]);
			}
		}

		newUser.setSteamId(profile.getSteamId64());
		newUser.setImageUrl(profile.getImageUrl());
		newUser.setSteamGame(profile.getGameName());
		newUser.setSteamOnline(profile.getOnlineState() == 0 ? false : true);
		newUser.setEmail(profile.getSteamId64() + "@localhost.net");
		newUser.setSteam(true);
		newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
		
		addUserRole(newUser);
		
		return newUser;
	}

	public User changePassword(final User user, final String newPassword) {
		user.setPassword(encodePassword(newPassword));
		return user;
	}

	public void addUserRole(final User user) {
		final Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.findUserRole());
		user.setRoles(roles);
	}
	
	public void addAdminRole(final User user) {
		user.getRoles().add(roleService.findAdminRole());
	}
	
	private String encodePassword(final String password) {
		return passwordEncoder.encode(password);
	}

	private String generateGravatarUrl(final String email) {
		return "http://www.gravatar.com/avatar/" + DigestUtils.md5Hex(email.trim().toLowerCase()) + "?d=identicon";
	}
}
