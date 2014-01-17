package com.quantasnet.qlan.web.service;

import java.util.Set;

import com.quantasnet.qlan.web.domain.Lan;

public interface LanService {
	public Lan getActiveLan();
	public Set<Lan> getFutureLans();
	public Lan createNewLan(Lan lan);
	public void deleteLan(Lan lan);
}
