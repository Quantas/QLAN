package com.quantasnet.qlan.web.repo;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.web.domain.Lan;

public interface LanRepository extends JpaRepository<Lan, Long> {
	
	public Set<Lan> findByStartGreaterThan(DateTime first);
}
