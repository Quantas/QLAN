package com.quantasnet.qlan.repo;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quantasnet.qlan.domain.Lan;

public interface LanRepository extends JpaRepository<Lan, Long> {
	
	public Set<Lan> findByStartGreaterThan(DateTime now);
	@Query("SELECT l FROM Lan l WHERE ?1 BETWEEN l.start and l.end")
	public Set<Lan> findCurrent(DateTime now);
}
