package com.quantasnet.qlan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.domain.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {

}
