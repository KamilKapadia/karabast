package io.github.kamilkapadia.karabast.util.rules;

import java.util.ArrayList;
import java.util.Arrays;
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
import io.github.kamilkapadia.karabast.util.JSONPathUtil;

public class RulesProcessingUtil {

	// TODO - read from the database
    public static final int UNKNOWN = 1;
    public static final int RUNNING_CORRECTLY = 2;
    public static final int SLOWER = 4;
    public static final int WARNING = 8;
    public static final int STALLED = 16;
    public static final int DOWN = 32;
    
    public static final int INT_TYPE = 1;
    public static final int DEC_TYPE = 2;
    public static final int STRING_TYPE = 4;
    public static final int BOOL_TYPE = 8;
    
    public static final int EQUALS                 = 1;
	public static final int NOT_EQUALS             = 2;
	public static final int BETWEEN                = 3;             
	public static final int NOT_BETWEEN            = 4;
	public static final int GREATER_THAN           = 5;
	public static final int LESS_THAN              = 6;
	public static final int GREATER_THAN_OR_EQUALS = 7;
	public static final int LESS_THAN_OR_EQUALS    = 8;
	public static final int IS_IN                  = 9;
	public static final int IS_NOT_IN              = 10;
	public static final int STARTS_WITH            = 11;
	public static final int ENDS_WITH              = 12;
	public static final int CONTAINS               = 13;
	
	public static List<RuleResult> validate(RuleService ruleService, Object document, Job job) {
		List<Rule> rules = ruleService.findByJobId(job.getId());
		List<RuleResult> ruleResults = new ArrayList<RuleResult>();
		
		List<Integer> results = new ArrayList<>();
		
		// TODO - this is going to have to do more for checking all rules...
		System.out.println("Number of rules: " + rules.size());
		
		for (Rule rule : rules) {
			boolean ruleActive = rule.isActive();
			
			if (ruleActive) {
				String ruleName = rule.getName();
				TypeCode typeCode = rule.getTypeCode();
				String valuePath = rule.getValuePath();
				RuleCode ruleCode = rule.getRuleCode();
				String expectedValueString = rule.getExpectedValue();
				StatusCode goodStatusCode = rule.getGoodStatusCode();
				StatusCode badStatusCode = rule.getBadStatusCode();
				
				String pathValueString = JSONPathUtil.getString(document, valuePath);
				
				// TODO
				System.err.println("---------------------------------------------------------------------------------");
				System.err.println("pathValueString: " + pathValueString);
				System.err.println("typeCode: " + typeCode);
				System.err.println("ruleCode: " + ruleCode);
				System.err.println("goodStatusCode: " + goodStatusCode);
				System.err.println("badStatusCode: " + badStatusCode);
				System.err.println("expectedValueString: " + expectedValueString);
				System.err.println("---------------------------------------------------------------------------------");
				// TODO
				
				int result = 1;
				
				if (INT_TYPE == typeCode.getId()) {
					result = IntegerRuleProcessingUtil.validate(Integer.parseInt(pathValueString), Integer.parseInt(expectedValueString), goodStatusCode.getId(), badStatusCode.getId(), ruleCode.getId());
					System.err.println("RESULT: " + result);
					results.add(result);
				} else if (DEC_TYPE == typeCode.getId()) {
					result = DecimalRuleProcessingUtil.validate(Double.parseDouble(pathValueString), Double.parseDouble(expectedValueString), goodStatusCode.getId(), badStatusCode.getId(), ruleCode.getId());
					System.err.println("RESULT: " + result);
					results.add(result);
				} else if (STRING_TYPE == typeCode.getId()) {
					result = StringRuleProcessingUtil.validate(pathValueString, expectedValueString, goodStatusCode.getId(), badStatusCode.getId(), ruleCode.getId());
					System.err.println("RESULT: " + result);
					results.add(result);
				} else if (BOOL_TYPE == typeCode.getId()) {
					
				} else {
					
				}
				
				//int ruleStatusCode = validateRule(pathValueString, expectedValueString, ruleCode.getId(), statusCode.getId(), clazz);
				
				// NAME: response = 200 OK (TypeCode [id=4, name=STRING] 
				//       RuleCode [id=1, name=EQUALS] StatusCode [id=2, name=RUNNING CORRECTLY] 200 OK true
				
				// NAME: contentLength = 12570 (TypeCode [id=1, name=INTEGER] RuleCode [id=1, name=EQUALS] 
				//       StatusCode [id=2, name=RUNNING CORRECTLY] 12570 true
				
				RuleResult ruleResult = new RuleResult();
				
				ruleResult.setRule(rule);
				ruleResult.setConditionMet(1);
				ruleResult.setReason("Expected " + pathValueString + " to " + ruleCode.getName() + " " + expectedValueString);
				
				//TODO
				System.err.println("Rule Result: " + ruleResult.getReason());
				
				ruleResults.add(ruleResult);
			}
		}
		
		// TODO
		System.err.println(results);
		// TODO
		System.err.println(ruleResults.size());
		
		return ruleResults;
	}
	
	public static StatusCode getStatusCode(StatusCodeService statusCodeService, List<RuleResult> ruleResults) {
		// TODO Auto-generated method stub
		
		for (RuleResult ruleResult : ruleResults) {
			// TODO
			System.out.println("Persist Results: " + ruleResult.getRule().getName());
		}
		
		return statusCodeService.findById(2);
	}

	public static void persist(RuleResultService ruleResultService, Result result, List<RuleResult> ruleResults) {
		
		for (RuleResult ruleResult : ruleResults) {
			ruleResult.setResult(result);
			
			// TODO
			System.out.println("Persist Results: " + ruleResult.getRule().getExpectedValue() + " " + ruleResult.getConditionMet() + " " + ruleResult.getReason());
			
			ruleResultService.save(ruleResult);	
		}
	}
}
