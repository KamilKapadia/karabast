package io.github.kamilkapadia.karabast.components.setup.rule;

import java.util.List;

public interface RuleDAO {

	public List<Rule> findAll();
	
	public Rule findById(long theId);
	
	public List<Rule> findByJobId(long theId);
	
	public void save(Rule theRule);
	
	public void deleteById(long theId);
}
