package io.github.kamilkapadia.karabast.service.setup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.setup.HistoricalNameDAO;
import io.github.kamilkapadia.karabast.dto.setup.HistoricalName;

@Service
public class HistoricalNameService {

	// The "regular" JPA WAY

	private HistoricalNameDAO historicalNameDAO;
	
	@Autowired
	public HistoricalNameService(HistoricalNameDAO theHistoricalNameDAO) {
		historicalNameDAO = theHistoricalNameDAO;
	}

	@Transactional
	public List<HistoricalName> findAll() {
		return historicalNameDAO.findAll();
	}

	@Transactional
	public HistoricalName findById(long theId) {
		return historicalNameDAO.findById(theId);
	}
	
	@Transactional
	public List<HistoricalName> findByJobId(long theId) {
		return historicalNameDAO.findByJobId(theId);
	}

	@Transactional
	public void save(HistoricalName theHistoricalName) {
		historicalNameDAO.save(theHistoricalName);
	}

	@Transactional
	public void deleteById(long theId) {
		historicalNameDAO.deleteById(theId);
	}
}
