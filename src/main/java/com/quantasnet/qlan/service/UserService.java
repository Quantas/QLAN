package com.quantasnet.qlan.service;

import java.util.List;
import java.util.Set;

import com.quantasnet.qlan.domain.Role;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.steam.api.SteamProfile;

public interface UserService {
	List<User> getAllUsers();
	List<User> getAllSteamUsers();
	List<User> findUsersByLanId(long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserBySteamId(long id);
    User getUserById(long id);
    User saveUser(User user);
    User saveOpenIdUser(final SteamProfile profile);
    User save(String userName, String firstName, String lastName, String email, String password, Set<Role> roles);
    User update(User user);
    User profileUpdate(User user);
    void deleteUser(long id);
    void deactivateUser(long id);
    void activateUser(long id);
    void makeAdmin(long id);
    void revokeAdmin(long id);
    
    void changePassword(User user, String password);
}
