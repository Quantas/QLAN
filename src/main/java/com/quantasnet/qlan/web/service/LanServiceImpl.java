package com.quantasnet.qlan.web.service;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantasnet.qlan.web.domain.Lan;
import com.quantasnet.qlan.web.repo.LanRepository;

@Service
public class LanServiceImpl implements LanService {

	private final LanRepository lanRepository;
	
	@Autowired
	public LanServiceImpl(final LanRepository lanRepository) {
		this.lanRepository = lanRepository;
	}
	
	@Override
	public Lan getActiveLan() {
		return null;
	}

	@Override
	public Set<Lan> getFutureLans() {
		return lanRepository.findByStartGreaterThan(DateTime.now());
	}
	
	@Override
	public Lan createNewLan(final Lan lan) {
		return lanRepository.saveAndFlush(lan);
	}

	@Override
	public void deleteLan(final Lan lan) {
		lanRepository.delete(lan);
	}
}
