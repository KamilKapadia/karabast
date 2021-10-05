package io.github.kamilkapadia.karabast.components.lookup.statuscode;

import java.util.List;

public interface StatusCodeDAO {
	
	public List<StatusCode> findAll();
	
	public StatusCode findById(int theId);
}
