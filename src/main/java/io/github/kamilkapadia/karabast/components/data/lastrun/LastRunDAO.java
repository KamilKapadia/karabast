package io.github.kamilkapadia.karabast.components.data.lastrun;

import java.util.List;


public interface LastRunDAO {
	public List<LastRun> findAll();
	
	public LastRun findById(long theId);
	
	public LastRun findByJobId(long theId);
	
	public void save(LastRun theLastRun);
	
	public void deleteById(long theId);
}
