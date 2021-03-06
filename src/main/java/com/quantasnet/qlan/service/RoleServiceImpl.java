package com.quantasnet.qlan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.quantasnet.qlan.domain.Role;
import com.quantasnet.qlan.repo.RoleRepository;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

	@Autowired
    public RoleServiceImpl(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
    public List<Role> findAll() {
        final Iterable<Role> roles = roleRepository.findAll();
        return Lists.newArrayList(roles);
    }

	@Override
	public Role findUserRole() {
		return roleRepository.findByRoleName(Role.USER);
	}
	
	@Override
	public Role findAdminRole() {
		return roleRepository.findByRoleName(Role.ADMIN);
	}
	
    @Override
    public Role save(final Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role save(final String roleName) {
        final Role role = new Role();
        role.setRoleName(roleName);

        return roleRepository.save(role);
    }
}