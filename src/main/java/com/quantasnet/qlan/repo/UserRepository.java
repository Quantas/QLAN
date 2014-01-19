package com.quantasnet.qlan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User getUserByUserName(String userName);
	User getUserBySteamId(long id);
}
