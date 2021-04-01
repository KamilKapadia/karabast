package io.github.kamilkapadia.karabast.dao.setup;

import java.util.List;

import io.github.kamilkapadia.karabast.dto.setup.Rule;

public interface RuleDAO {

	public List<Rule> findAll();
	
	public Rule findById(long theId);
	
	public List<Rule> findByJobId(long theId);
	
	public void save(Rule theRule);
	
	public void deleteById(long theId);
}
