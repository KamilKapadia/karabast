package io.github.kamilkapadia.karabast.util;

import java.util.ArrayList;
import java.util.List;

import io.github.kamilkapadia.karabast.components.data.result.Result;
import io.github.kamilkapadia.karabast.components.data.ruleresult.RuleResult;
import io.github.kamilkapadia.karabast.components.data.ruleresult.RuleResultService;
import io.github.kamilkapadia.karabast.components.lookup.rulecode.RuleCode;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCode;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCodeService;
import io.github.kamilkapadia.karabast.components.lookup.typecode.TypeCode;
import io.github.kamilkapadia.karabast.components.setup.job.Job;
import io.github.kamilkapadia.karabast.components.setup.rule.Rule;
import io.github.kamilkapadia.karabast.components.setup.rule.RuleService;

public class RulesProcessingUtil {

	public static List<RuleResult> validate(RuleService ruleService, Object document, Job job) {
		List<Rule> rules = ruleService.findByJobId(job.getId());
		List<RuleResult> ruleResults = new ArrayList<RuleResult>();
		
		// TODO - this is going to have to do more for checking all rules...
		for (Rule rule : rules) {
			boolean ruleActive = rule.isActive();
			
			if (ruleActive) {
				String ruleName = rule.getName();
				TypeCode typeCode = rule.getTypeCode();
				String valuePath = rule.getValuePath();
				RuleCode ruleCode = rule.getRuleCode();
				String expectedValueString = rule.getExpectedValue();
				StatusCode statusCode = rule.getStatusCode();
				
				String ruleString = JSONPathUtil.getString(document, valuePath);
				
				// NAME: response = 200 OK (TypeCode [id=4, name=STRING] 
				//       RuleCode [id=1, name=EQUALS] StatusCode [id=2, name=RUNNING CORRECTLY] 200 OK true
				
				// NAME: contentLength = 12570 (TypeCode [id=1, name=INTEGER] RuleCode [id=1, name=EQUALS] 
				//       StatusCode [id=2, name=RUNNING CORRECTLY] 12570 true
				
				RuleResult ruleResult = new RuleResult();
				
				ruleResult.setRule(rule);
				ruleResult.setConditionMet(1);
				ruleResult.setReason("Test Reason");
				
				ruleResults.add(ruleResult);
			}
		}
		
		return ruleResults;
	}

	public static StatusCode getStatusCode(StatusCodeService statusCodeService, List<RuleResult> ruleResults) {
		// TODO Auto-generated method stub
		return statusCodeService.findById(2);
	}

	public static void persist(RuleResultService ruleResultService, Result result, List<RuleResult> ruleResults) {
		
		for (RuleResult ruleResult : ruleResults) {
			ruleResult.setResult(result);
			ruleResultService.save(ruleResult);	
		}
	}
}
