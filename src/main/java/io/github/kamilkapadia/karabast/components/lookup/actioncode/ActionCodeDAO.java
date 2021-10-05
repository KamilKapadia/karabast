package io.github.kamilkapadia.karabast.components.lookup.actioncode;

import java.util.List;

public interface ActionCodeDAO {
	public List<ActionCode> findAll();
	
	public ActionCode findById(int theId);
}
