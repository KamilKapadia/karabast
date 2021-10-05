package io.github.kamilkapadia.karabast.components.data.historicaldata;

import java.util.List;


public interface HistoricalDataDAO {

	public List<HistoricalData> findAll();
	
	public HistoricalData findById(long theId);
	
	public List<HistoricalData> findByResultId(long resultId);
	
	public void save(HistoricalData theHistoricalData);
	
	public void deleteById(long theId);
}

