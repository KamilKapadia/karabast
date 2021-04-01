package io.github.kamilkapadia.karabast.dao.lookup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;

public interface ActionCodeDAO {
	public List<ActionCode> findAll();
	
	public ActionCode findById(int theId);
}
