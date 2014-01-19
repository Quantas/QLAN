package com.quantasnet.qlan.web.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantasnet.qlan.web.components.SteamAPI;
import com.quantasnet.qlan.web.components.UserFactory;
import com.quantasnet.qlan.web.domain.Role;
import com.quantasnet.qlan.web.domain.User;
import com.quantasnet.qlan.web.repo.UserRepository;

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

	@Transactional(readOnly = true)
	@Override
	public User getUserByUsername(final String username) {
		return userRepository.getUserByUserName(username);
	}

	@Transactional(readOnly = true)
	@Override
	public User getUserBySteamId(long id) {
		return userRepository.getUserBySteamId(id);
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
	public User saveOpenIdUser(final SteamAPI.Profile profile) {
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
}
