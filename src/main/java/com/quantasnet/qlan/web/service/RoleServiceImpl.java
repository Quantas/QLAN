package com.quantasnet.qlan.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.quantasnet.qlan.web.domain.Role;
import com.quantasnet.qlan.web.repo.RoleRepository;

@Transactional
@Service
public class RoleServiceImpl implements RoleService
{
    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll()
    {
        final Iterable<Role> roles = roleRepository.findAll();
        return Lists.newArrayList(roles);
    }

    @Override
    public Role save(final Role role)
    {
        return roleRepository.save(role);
    }

    @Override
    public Role save(final String roleName)
    {
        final Role role = new Role();
        role.setRoleName(roleName);

        return roleRepository.save(role);
    }
}