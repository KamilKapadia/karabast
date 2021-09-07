package io.github.kamilkapadia.karabast.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.data.LastRunDAO;
import io.github.kamilkapadia.karabast.dto.data.LastRun;
import io.github.kamilkapadia.karabast.dto.data.Result;

@Service
public class LastRunService {

	// The "regular" JPA WAY
	private LastRunDAO lastRunDAO;

	@Autowired
	public LastRunService(LastRunDAO theLastRunDAO) {
		lastRunDAO = theLastRunDAO;
	}

	@Transactional
	public List<LastRun> findAll() {
		return lastRunDAO.findAll();
	}

	@Transactional
	public LastRun findById(long theId) {
		return lastRunDAO.findById(theId);
	}

	@Transactional
	public LastRun findByJobId(long theId) {
		return lastRunDAO.findByJobId(theId);
	}
	
	@Transactional
	public void save(LastRun theLastRun) {
		lastRunDAO.save(theLastRun);
	}
}
