package io.github.kamilkapadia.karabast.dao.setup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.setup.HistoricalName;

public interface HistoricalNameDAO {

	public List<HistoricalName> findAll();
	
	public HistoricalName findById(long theId);
	
	public List<HistoricalName> findByJobId(long theId);
	
	public void save(HistoricalName historicalName);
	
	public void deleteById(long theId);
}
