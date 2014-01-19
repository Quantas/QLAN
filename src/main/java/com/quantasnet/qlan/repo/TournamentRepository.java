package com.quantasnet.qlan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantasnet.qlan.domain.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
