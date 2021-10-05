package io.github.kamilkapadia.karabast.components.lookup.rulecode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RuleCodeService {

	private RuleCodeDAO ruleCodeDAO;
	
	@Autowired
	public RuleCodeService(RuleCodeDAO theRuleCodeDAO) {
		ruleCodeDAO = theRuleCodeDAO;
	}

	@Transactional
	public List<RuleCode> findAll() {
		return ruleCodeDAO.findAll();
	}

	@Transactional
	public RuleCode findById(int theId) {
		return ruleCodeDAO.findById(theId);
	}
}

