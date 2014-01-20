package com.quantasnet.qlan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
