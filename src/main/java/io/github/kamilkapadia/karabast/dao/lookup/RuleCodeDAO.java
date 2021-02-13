package io.github.kamilkapadia.karabast.dao.lookup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.lookup.RuleCode;

public interface RuleCodeDAO {
	
	public List<RuleCode> findAll();
	
	public RuleCode findById(int theId);
}
