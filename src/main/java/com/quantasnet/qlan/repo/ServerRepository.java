package com.quantasnet.qlan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quantasnet.qlan.domain.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
	List<Server> findBySteam(Boolean steam);
	
	@Query(nativeQuery = true, value = "SELECT s.* FROM server s INNER JOIN lan_server l ON l.servers_id = s.id WHERE l.lan_id = ?1 ORDER BY s.game ASC")
	List<Server> findServersByLanId(long id);
}
