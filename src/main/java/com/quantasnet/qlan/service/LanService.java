package com.quantasnet.qlan.service;

import java.util.Set;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.domain.User;

public interface LanService {
	Set<Lan> getActiveLans();
	Set<Lan> getFutureLans();
	Lan createNewLan(Lan lan);
	Lan findOne(long id);
	void deleteLan(Lan lan);
	void joinLan(User user, long id);
	void leaveLan(User user, long id);
}
