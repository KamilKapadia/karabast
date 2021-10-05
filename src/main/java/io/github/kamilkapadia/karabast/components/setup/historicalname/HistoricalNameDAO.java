package io.github.kamilkapadia.karabast.components.setup.historicalname;

import java.util.List;

public interface HistoricalNameDAO {

	public List<HistoricalName> findAll();
	
	public HistoricalName findById(long theId);
	
	public List<HistoricalName> findByJobId(long theId);
	
	public void save(HistoricalName historicalName);
	
	public void deleteById(long theId);
}
