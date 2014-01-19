package com.quantasnet.qlan.web.service;

import java.util.List;
import java.util.Set;

import com.quantasnet.qlan.web.components.SteamAPI;
import com.quantasnet.qlan.web.domain.Role;
import com.quantasnet.qlan.web.domain.User;

public interface UserService {
	List<User> getAllUsers();
    User getUserByUsername(String username);
    User getUserBySteamId(long id);
    User saveUser(User user);
    User saveOpenIdUser(final SteamAPI.Profile profile);
    User save(String userName, String firstName, String lastName, String email, String password, Set<Role> roles);
    User update(User user);
    void deleteUser(long id);
    void deactivateUser(long id);
    void activateUser(long id);
    
    void changePassword(User user, String password);
}
