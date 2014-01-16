package com.quantasnet.qlan.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.web.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User getUserByUserName(String userName);
}
