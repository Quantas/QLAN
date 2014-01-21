package com.quantasnet.qlan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quantasnet.qlan.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User getUserByUserName(String userName);
	User getUserBySteamId(long id);
	List<User> findBySteam(boolean steam);
	@Query(nativeQuery = true, value = "SELECT u.* FROM user u INNER JOIN lan_user l ON l.users_id = u.id WHERE l.lan_id = ?1 ORDER BY u.user_name ASC")
	List<User> findUsersByLanId(long id);
}
