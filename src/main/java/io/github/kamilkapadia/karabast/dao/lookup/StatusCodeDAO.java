package io.github.kamilkapadia.karabast.dao.lookup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;

public interface StatusCodeDAO {
	
	public List<StatusCode> findAll();
	
	public StatusCode findById(int theId);
}
