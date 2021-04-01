package io.github.kamilkapadia.karabast.dao.data;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.data.RuleResult;

public interface RuleResultDAO {
	public List<RuleResult> findAll();
	
	public RuleResult findById(long theId);
	
	public List<RuleResult> findByResultId(long theId);
	
	public void save(RuleResult theRuleResult);
	
	public void deleteById(long theId);
}

