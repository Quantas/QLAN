package com.quantasnet.qlan.components;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.quantasnet.qlan.domain.Role;
import com.quantasnet.qlan.service.RoleService;
import com.quantasnet.qlan.service.UserService;

@Component
public class DatabaseFillerListener implements ApplicationListener<ContextRefreshedEvent>
{
    private final Logger log = LoggerFactory.getLogger(DatabaseFillerListener.class);

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent)
    {
    	try {
	        final List<Role> roles = roleService.findAll();
	
	        if (roles.isEmpty())
	        {
	            // create roles
	            final Set<Role> rolesAll = new HashSet<Role>();
	            final Set<Role> rolesUser = new HashSet<Role>();
	
	            final Role user = roleService.save("ROLE_USER");
	            final Role admin = roleService.save("ROLE_ADMIN");
	
	            rolesAll.add(user);
	            rolesAll.add(admin);
	
	            rolesUser.add(user);
	
	            // create users
	            userService.save("admin", "Admin", "Administrator", "admin@test.com", "admin", rolesAll);
	            userService.save("user", "User", "Userton", "user@test.com", "user", rolesUser);
	        }
	        else
	        {
	            log.info("DB already populated");
	        }
    	}
        catch(final Exception e) {
        	log.error("Error filling the database", e);
        }
    }
}