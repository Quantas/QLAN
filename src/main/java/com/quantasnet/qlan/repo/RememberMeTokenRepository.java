package com.quantasnet.qlan.repo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.quantasnet.qlan.domain.RememberMeToken;

public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, String> {
	@Modifying
    @Query("UPDATE RememberMeToken r SET r.token = ?1, r.lastUsed = ?2 WHERE r.series = ?3")
    void updateToken(String token, Date lastUsed, String series);

    @Modifying
    @Query("DELETE FROM RememberMeToken r WHERE r.username = ?1")
    void removeTokensForUser(String username);
}
