package io.github.kamilkapadia.karabast.dao.setup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.setup.Action;

public interface ActionDAO {

	public List<Action> findAll();
	
	public Action findById(long theId);
	
	public List<Action> findByJobId(long theId);
	
	public void save(Action theAction);
	
	public void deleteById(long theId);
}

