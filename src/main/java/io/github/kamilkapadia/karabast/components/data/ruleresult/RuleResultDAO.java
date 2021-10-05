package io.github.kamilkapadia.karabast.components.data.ruleresult;

import java.util.List;

public interface RuleResultDAO {
	public List<RuleResult> findAll();
	
	public RuleResult findById(long theId);
	
	public List<RuleResult> findByResultId(long theId);
	
	public void save(RuleResult theRuleResult);
	
	public void deleteById(long theId);
}

