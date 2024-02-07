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
import io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleData;
import io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleResult;

public class RulesProcessingUtil {
	
	private static final String RULE_TEMPLATE = 
			"rule \"{0}\"\r\n" + 
    		"    when\r\n" + 
    		"        ruleDataInstance:DroolsRuleData({1});\r\n" + 
    		"    then\r\n" + 
    		"        droolsRuleResult.addResult(\"{2}\", true);\r\n" + 
    		"end" +
    		"\r\n" +
    		"\r\n";
	
	public static List<RuleResult> validate(RuleService ruleService, Object document, Job job) {
		
		System.out.println("*********************************************************************************************************************************************************** validate: " + job.getName());
		
		List<Rule> rules = ruleService.findByJobId(job.getId());
		List<RuleResult> ruleResults = new ArrayList<RuleResult>();
		List<String> enRules = new ArrayList<>();
		List<String> ruleStrings = new ArrayList<>();
		
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

				String currentEnString = typeCode.getName() + " " + pathValueString + " " + ruleCode.getName() + " " + expectedValueString;
				String currenMvelString = convertStringToCode(currentEnString);
				String currentrule = RULE_TEMPLATE.replace("{0}", ruleName).replace("{1}", currenMvelString).replace("{2}", currentEnString);
				
				enRules.add(currentEnString);
		        ruleStrings.add(currentrule);

		        RuleConfiguration ruleConfiguration = new RuleConfiguration(ruleStrings);
		        RuleDataCalculationService ruleDataCalculationService = new RuleDataCalculationService(ruleConfiguration);
				
		        DroolsRuleData ruleData = new DroolsRuleData();
		        
		        DroolsRuleResult ruleResult = new DroolsRuleResult(enRules.toArray(new String[enRules.size()]));
		        String retval = ruleDataCalculationService.calculate(ruleData, ruleResult);
				

		        System.err.println(ruleResult);
		        
				RuleResult rulezResult = new RuleResult();
				
				rulezResult.setRule(rule);
				rulezResult.setConditionMet(1);
				rulezResult.setReason("Expected " + pathValueString + " to " + ruleCode.getName() + " " + expectedValueString);
				
				//TODO
				System.err.println("Rule Result: " + rulezResult.getReason());
				
				ruleResults.add(rulezResult);
			}
		}
		
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
	
	
	
	
	
	private static String convertStringToCode(String text) {
    	StringBuilder builder = new StringBuilder();
    	
    	text = text.replace("(", " ( ");
    	text = text.replace(")", " ) ");
    	
    	String parts[] = text.split(" ");
    	
    	for (int i = 0; i < parts.length; i++) {
    		
    		if ("(".equals(parts[i]))            { builder.append(parts[i]); } 
    		else if (")".equals(parts[i]))       { builder.append(parts[i]); } 
    		else if ("AND".equals(parts[i]))     { builder.append(" && ");   } 
    		else if ("OR".equals(parts[i]))      { builder.append(" || ");   } 
    		else if ("INTEGER".equals(parts[i])) {
    			builder.append(" getLongValue(\"");
    			builder.append(parts[++i]);
    			builder.append("\") ");
    			
    			i++;
    			
    			if ("EQUALS".equals(parts[i])) { builder.append(" == "); } 
    			else if ("LESS-THAN".equals(parts[i])) { builder.append(" < "); }
    			
    			builder.append(parts[++i]);
    			
    		} else if ("BOOLEAN".equals(parts[i])) {
    			builder.append(" getBooleanValue(\"");
    			builder.append(parts[++i]);
    			builder.append("\") ");
    			
    			i++;
    			
    			if ("EQUALS".equals(parts[i])) { builder.append(" == "); }
    			
    			builder.append(parts[++i]);
    		}
    	}
    	
    	return builder.toString().replaceAll("[ ]+", " ");
    }
}
