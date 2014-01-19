package com.quantasnet.qlan.service;

import java.util.Set;

import com.quantasnet.qlan.domain.Lan;

public interface LanService {
	public Set<Lan> getActiveLans();
	public Set<Lan> getFutureLans();
	public Lan createNewLan(Lan lan);
	public void deleteLan(Lan lan);
}
