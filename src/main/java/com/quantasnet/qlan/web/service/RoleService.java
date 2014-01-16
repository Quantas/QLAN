package com.quantasnet.qlan.web.service;

import java.util.List;

import com.quantasnet.qlan.web.domain.Role;

public interface RoleService {
	List<Role> findAll();
    Role save(Role role);
    Role save(String roleName);
}
