package io.github.kamilkapadia.karabast.dao.data;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.LastRun;


public interface LastRunDAO {
	public List<LastRun> findAll();
	
	public LastRun findById(long theId);
	
	public LastRun findByJobId(long theId);
	
	public void save(LastRun theLastRun);
	
	public void deleteById(long theId);
}
