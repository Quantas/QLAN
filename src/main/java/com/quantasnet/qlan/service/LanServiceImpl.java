package com.quantasnet.qlan.service;

import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantasnet.qlan.domain.Lan;
import com.quantasnet.qlan.domain.User;
import com.quantasnet.qlan.repo.LanRepository;

@Transactional
@Service
public class LanServiceImpl implements LanService {

	private final LanRepository lanRepository;
	
	@Autowired
	public LanServiceImpl(final LanRepository lanRepository) {
		this.lanRepository = lanRepository;
	}
	
	@Override
	public Set<Lan> getActiveLans() {
		return lanRepository.findCurrent(DateTime.now());
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
	public Lan findOne(final long id) {
		return lanRepository.findOne(id);
	}
	
	@Override
	public void deleteLan(final Lan lan) {
		lanRepository.delete(lan);
	}

	@Override
	public void joinLan(final User user, final long id) {
		final Lan lan = lanRepository.findOne(id);

		boolean joined = false;
		
		for (final User attendee : lan.getUsers()) {
			if (attendee.getId().equals(user.getId())) {
				joined = true;
			}
		}
		
		if (!joined){
			lan.getUsers().add(user);
			lanRepository.saveAndFlush(lan);
		}
	}

	@Override
	public void leaveLan(final User user, final long id) {
		final Lan lan = lanRepository.findOne(id);

		User toDelete = null;		
		for (final User attendee : lan.getUsers()) {
			if (attendee.getId().equals(user.getId())) {
				toDelete = attendee;
			}
		}
		
		lan.getUsers().remove(toDelete);
		lanRepository.saveAndFlush(lan);
	}
}