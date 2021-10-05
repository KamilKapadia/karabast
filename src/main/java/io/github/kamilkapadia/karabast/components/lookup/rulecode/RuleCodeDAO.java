package io.github.kamilkapadia.karabast.components.lookup.rulecode;

import java.util.List;

public interface RuleCodeDAO {
	
	public List<RuleCode> findAll();
	
	public RuleCode findById(int theId);
}
