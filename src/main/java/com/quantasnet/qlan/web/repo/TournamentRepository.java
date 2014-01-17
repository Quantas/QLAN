package com.quantasnet.qlan.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.web.domain.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
