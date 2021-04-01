package io.github.kamilkapadia.karabast.dao.data;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.HistoricalData;


public interface HistoricalDataDAO {

	public List<HistoricalData> findAll();
	
	public HistoricalData findById(long theId);
	
	public List<HistoricalData> findByResultId(long resultId);
	
	public void save(HistoricalData theHistoricalData);
	
	public void deleteById(long theId);
}

