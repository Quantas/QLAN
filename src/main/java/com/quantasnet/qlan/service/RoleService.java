package com.quantasnet.qlan.service;

import java.util.List;

import com.quantasnet.qlan.domain.Role;

public interface RoleService {
	List<Role> findAll();
    Role save(Role role);
    Role save(String roleName);
}
