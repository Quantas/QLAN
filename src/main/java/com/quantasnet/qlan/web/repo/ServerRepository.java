package com.quantasnet.qlan.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.web.domain.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {

}
