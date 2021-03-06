package com.quantasnet.qlan.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantasnet.qlan.components.UserFactory;
import com.quantasnet.qlan.domain.Role;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.repo.UserRepository;
import com.quantasnet.qlan.steam.api.SteamProfile;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserFactory userFactory;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public List<User> getAllSteamUsers() {
		return userRepository.findBySteam(Boolean.TRUE);
	}

	@Override
	public List<User> findUsersByLanId(long id) {
		return userRepository.findUsersByLanId(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public User getUserByUsername(final String username) {
		return userRepository.getUserByUserName(username);
	}

	@Override
	public User getUserByEmail(final String email) {
		return userRepository.getUserByEmail(email);
	}
	
	@Transactional(readOnly = true)
	@Override
	public User getUserBySteamId(long id) {
		return userRepository.getUserBySteamId(id);
	}
	
	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public User saveUser(final User user) {
		if (null == userRepository.getUserByUserName(user.getUserName())) {
			final User returnUser = userFactory.make(user);
			return userRepository.saveAndFlush(returnUser);
		}
		return null;
	}
	
	@Override
	public User saveOpenIdUser(final SteamProfile profile) {
		final User newUser = userFactory.makeSteamUser(profile);
		return userRepository.saveAndFlush(newUser);
	}

	@Override
	public User save(final String userName, final String firstName,
			final String lastName, final String email, final String password,
			final Set<Role> roles) {
		final User user = userFactory.make(userName, firstName, lastName,
				email, password, roles);
		return userRepository.saveAndFlush(user);
	}
	
	@Override
	public User update(final User user) {
		return userRepository.saveAndFlush(user);
	}
	
	@Override
	public User profileUpdate(final User profileUser) {
		final User dbUser = userRepository.findOne(profileUser.getId());
		dbUser.setFirstName(profileUser.getFirstName());
		dbUser.setLastName(profileUser.getLastName());
		dbUser.setEmail(profileUser.getEmail());
		dbUser.setImageUrl(userFactory.generateGravatarUrl(dbUser.getEmail()));
		final User newUser = userRepository.saveAndFlush(dbUser);
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(newUser, newUser.getPassword(), newUser.getAuthorities()));
		return newUser;
	}

	@Override
	public void deleteUser(final long id) {
		userRepository.delete(id);
	}

	@Override
	public void deactivateUser(final long id) {
		final User user = userRepository.findOne(id);
		user.setActive(false);
		userRepository.saveAndFlush(user);
	}

	@Override
	public void activateUser(final long id) {
		final User user = userRepository.findOne(id);
		user.setActive(true);
		userRepository.saveAndFlush(user);
	}

	@Override
	public void changePassword(final User user, final String password) {
		final User saveUser = userFactory.changePassword(user, password);
		userRepository.saveAndFlush(saveUser);
	}
	
	@Override
    public void makeAdmin(long id) {
		final User user = userRepository.findOne(id);
		userFactory.addAdminRole(user);
		userRepository.saveAndFlush(user);
	}
	
	@Override
    public void revokeAdmin(long id) {
		final User user = userRepository.findOne(id);
		Role toRemove = null;
		for (final Role role : user.getRoles()) {
			if (role.getRoleName().equals(Role.ADMIN)) {
				toRemove = role;
				break;
			}
		}
		
		user.getRoles().remove(toRemove);
		userRepository.saveAndFlush(user);
	}
}
