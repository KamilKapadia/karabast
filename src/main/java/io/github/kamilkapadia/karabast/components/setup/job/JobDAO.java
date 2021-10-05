package io.github.kamilkapadia.karabast.components.setup.job;

import java.util.List;

public interface JobDAO {

	public List<Job> findAll();
	
	public Job findById(long theId);
	
	public List<Job> findByName(String name);
	
	public void save(Job theJob);
	
	public void deleteById(long theId);
}
