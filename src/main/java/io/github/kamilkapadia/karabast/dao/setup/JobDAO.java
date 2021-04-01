package io.github.kamilkapadia.karabast.dao.setup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.setup.Job;

public interface JobDAO {

	public List<Job> findAll();
	
	public Job findById(long theId);
	
	public List<Job> findByName(String name);
	
	public void save(Job theJob);
	
	public void deleteById(long theId);
}
