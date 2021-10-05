package io.github.kamilkapadia.karabast.components.setup.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobService {

	// The "regular" JPA WAY

	private JobDAO jobDAO;
	
	@Autowired
	public JobService(JobDAO theJobDAO) {
		jobDAO = theJobDAO;
	}

	@Transactional
	public List<Job> findAll() {
		return jobDAO.findAll();
	}

	@Transactional
	public Job findById(long theId) {
		return jobDAO.findById(theId);
	}

	@Transactional
	public List<Job> findByName(String name) {
		return jobDAO.findByName(name);
	}
	
	@Transactional
	public void save(Job theJob) {
		jobDAO.save(theJob);
	}

	@Transactional
	public void deleteById(long theId) {
		jobDAO.deleteById(theId);
	}
}

