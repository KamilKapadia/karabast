package io.github.kamilkapadia.karabast.service.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.data.HistoricalDataDAO;
import io.github.kamilkapadia.karabast.dto.data.HistoricalData;

@Service
public class HistoricalDataService {

	private HistoricalDataDAO historicalDataDAO;
	
	@Autowired
	public HistoricalDataService(HistoricalDataDAO theHistoricalDataDAO) {
		historicalDataDAO = theHistoricalDataDAO;
	}
	
	@Transactional
	public List<HistoricalData> findAll() {
		return historicalDataDAO.findAll();
	}

	@Transactional
	public HistoricalData findById(long theId) {
		return historicalDataDAO.findById(theId);
	}

	@Transactional
	public List<HistoricalData> findByResultId(long theId) {
		return historicalDataDAO.findByResultId(theId);
	}

	@Transactional
	public void save(HistoricalData theHisoricalData) {
		historicalDataDAO.save(theHisoricalData);
	}

	@Transactional
	public void deleteById(long theId) {
		historicalDataDAO.deleteById(theId);
	}
}
