package io.github.kamilkapadia.karabast.service.setup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.kamilkapadia.karabast.dao.setup.RuleDAO;
import io.github.kamilkapadia.karabast.dto.setup.Rule;

@Service
public class RuleService {
	// The "regular" JPA WAY

	private RuleDAO ruleDAO;

	@Autowired
	public RuleService(RuleDAO theRuleDAO) {
		ruleDAO = theRuleDAO;
	}

	@Transactional
	public List<Rule> findAll() {
		return ruleDAO.findAll();
	}

	@Transactional
	public Rule findById(long theId) {
		return ruleDAO.findById(theId);
	}

	@Transactional
	public List<Rule> findByJobId(long theId) {
		return ruleDAO.findByJobId(theId);
	}
	
	@Transactional
	public void save(Rule theRule) {
		ruleDAO.save(theRule);
	}

	@Transactional
	public void deleteById(long theId) {
		ruleDAO.deleteById(theId);
	}
}

