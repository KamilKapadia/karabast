package io.github.kamilkapadia.karabast.components.setup.action;

import java.util.List;

public interface ActionDAO {

	public List<Action> findAll();
	
	public Action findById(long theId);
	
	public List<Action> findByJobId(long theId);
	
	public void save(Action theAction);
	
	public void deleteById(long theId);
}

