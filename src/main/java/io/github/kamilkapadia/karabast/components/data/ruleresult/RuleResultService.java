package io.github.kamilkapadia.karabast.components.data.ruleresult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuleResultService {

	// The "regular" JPA WAY
	private RuleResultDAO ruleResultDAO;

	@Autowired
	public RuleResultService(RuleResultDAO theRuleResultDAO) {
		ruleResultDAO = theRuleResultDAO;
	}

	@Transactional
	public List<RuleResult> findAll() {
		return ruleResultDAO.findAll();
	}

	@Transactional
	public RuleResult findById(long theId) {
		return ruleResultDAO.findById(theId);
	}

	@Transactional
	public List<RuleResult> findByResultId(long theId) {
		return ruleResultDAO.findByResultId(theId);
	}
	
	@Transactional
	public void save(RuleResult theRuleResult) {
		ruleResultDAO.save(theRuleResult);
	}

}
