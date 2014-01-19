package com.quantasnet.qlan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.domain.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
	List<Server> findBySteam(Boolean steam);
}
