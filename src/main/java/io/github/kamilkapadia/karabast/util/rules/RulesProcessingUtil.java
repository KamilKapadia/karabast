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

	// TODO - read from the database
//    public static final int UNKNOWN = 1;
//    public static final int RUNNING_CORRECTLY = 2;
//    public static final int SLOWER = 4;
//    public static final int WARNING = 8;
//    public static final int STALLED = 16;
//    public static final int DOWN = 32;
//    
//    public static final int INT_TYPE = 1;
//    public static final int DEC_TYPE = 2;
//    public static final int STRING_TYPE = 4;
//    public static final int BOOL_TYPE = 8;
//    
//    public static final int EQUALS                 = 1;
//	public static final int NOT_EQUALS             = 2;
//	public static final int BETWEEN                = 3;             
//	public static final int NOT_BETWEEN            = 4;
//	public static final int GREATER_THAN           = 5;
//	public static final int LESS_THAN              = 6;
//	public static final int GREATER_THAN_OR_EQUALS = 7;
//	public static final int LESS_THAN_OR_EQUALS    = 8;
//	public static final int IS_IN                  = 9;
//	public static final int IS_NOT_IN              = 10;
//	public static final int STARTS_WITH            = 11;
//	public static final int ENDS_WITH              = 12;
//	public static final int CONTAINS               = 13;
	
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
		
		//List<Integer> results = new ArrayList<>();
		
		List<String> enRules = new ArrayList<>();
		List<String> ruleStrings = new ArrayList<>();
        
		
		// TODO - this is going to have to do more for checking all rules...
//		System.out.println("Number of rules: " + rules.size());
		
		// STRING "$.data.results.response" EQUALS 200 OK ? RUNNING CORRECTLY : DOWN
		// INTEGER "$.data.results.contentLength" EQUALS 12575 ? RUNNING CORRECTLY : DOWN
		
		for (Rule rule : rules) {
			boolean ruleActive = rule.isActive();
			
			if (ruleActive) {
				
//				// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//				
//				String enString1 = "BOOLEAN isNightSurcharge EQUALS false";
//		    	String enString2 = "INTEGER distanceInMile LESS-THAN 10";
//		    	String enString_full = "(BOOLEAN isNightSurcharge EQUALS false AND INTEGER distanceInMile LESS-THAN 10)";
//		    	
//		    	String mvelString1 = convertStringToCode(enString1);
//		    	String mvelString2 = convertStringToCode(enString2);
//		        String mvelString_full = convertStringToCode(enString_full);
//		        
//		        /*
//		         "{0}"                                           // name
//    		     when 
//    		        ruleDataInstance:DroolsRuleData("{1}");      // mvelString
//    		     then 
//    		        droolsRuleResult.addResult("{2}", true);     // enString1 (database)
//    		     end
//		         */
//		        String rule1 = RULE_TEMPLATE.replace("{0}", "Rule 1").replace("{1}", mvelString1).replace("{2}", enString1);
//		        String rule2 = RULE_TEMPLATE.replace("{0}", "Rule 2").replace("{1}", mvelString2).replace("{2}", enString2);
//		        String rule3 = RULE_TEMPLATE.replace("{0}", "Rule 3").replace("{1}", mvelString_full).replace("{2}", enString_full);
//		        
//		        /*
//		         Rule 1
//					enString1: BOOLEAN isNightSurcharge EQUALS false
//					mvelString1:  getBooleanValue("isNightSurcharge") == false
//					rule1: rule "Rule 1"
//    				when
//        				ruleDataInstance:DroolsRuleData( getBooleanValue("isNightSurcharge") == false);
//    				then
//        				droolsRuleResult.addResult("BOOLEAN isNightSurcharge EQUALS false", true);
//					end
//		         */
//		        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
//		        System.out.println("Rule 1");
//		        System.out.println("\tenString1: " + enString1);
//		        System.out.println("\tmvelString1: " + mvelString1);
//		        System.out.println("\trule1: " + rule1);
//		        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^`");
//		        
//		        
//		        List<String> ruleStrings = new ArrayList<>();
//		        ruleStrings.add(rule1);
//		        ruleStrings.add(rule2);
//		        ruleStrings.add(rule3);
//		        
//		        RuleConfiguration ruleConfiguration = new RuleConfiguration(ruleStrings);
//		        RuleDataCalculationService ruleDataCalculationService = new RuleDataCalculationService(ruleConfiguration);
//				
//		        DroolsRuleData ruleData = new DroolsRuleData();
//		        ruleData.setBooleanValue("isNightSurcharge", false);
//		        ruleData.setLongValue("distanceInMile", 9L);
//		        
//		        DroolsRuleResult ruleResult = new DroolsRuleResult(enString1, enString2, enString_full);
//		        String retval = ruleDataCalculationService.calculate(ruleData, ruleResult);
//		        System.out.println();
//		        System.out.println(retval);
//				
//				// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				
				String ruleName = rule.getName();
				TypeCode typeCode = rule.getTypeCode();
				String valuePath = rule.getValuePath();
				RuleCode ruleCode = rule.getRuleCode();
				String expectedValueString = rule.getExpectedValue();
				StatusCode goodStatusCode = rule.getGoodStatusCode();
				StatusCode badStatusCode = rule.getBadStatusCode();
				
				String pathValueString = JSONPathUtil.getString(document, valuePath);
				
				// TODO
//				System.err.println("---------------------------------------------------------------------------------");
//				System.err.println("pathValueString: " + pathValueString);
//				System.err.println("typeCode: " + typeCode);
//				System.err.println("ruleCode: " + ruleCode);
//				System.err.println("goodStatusCode: " + goodStatusCode);
//				System.err.println("badStatusCode: " + badStatusCode);
//				System.err.println("expectedValueString: " + expectedValueString);
//				System.err.println("---------------------------------------------------------------------------------");
				// TODO
				
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
				
		        System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		        System.err.println(currentrule);
		        System.err.println(retval);
		        System.err.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
								
				int result = 1;
				
//				if (INT_TYPE == typeCode.getId()) {
//					result = IntegerRuleProcessingUtil.validate(Integer.parseInt(pathValueString), Integer.parseInt(expectedValueString), goodStatusCode.getId(), badStatusCode.getId(), ruleCode.getId());
//					System.err.println("RESULT: " + result);
//					results.add(result);
//				} else if (DEC_TYPE == typeCode.getId()) {
//					result = DecimalRuleProcessingUtil.validate(Double.parseDouble(pathValueString), Double.parseDouble(expectedValueString), goodStatusCode.getId(), badStatusCode.getId(), ruleCode.getId());
//					System.err.println("RESULT: " + result);
//					results.add(result);
//				} else if (STRING_TYPE == typeCode.getId()) {
//					result = StringRuleProcessingUtil.validate(pathValueString, expectedValueString, goodStatusCode.getId(), badStatusCode.getId(), ruleCode.getId());
//					System.err.println("RESULT: " + result);
//					results.add(result);
//				} else if (BOOL_TYPE == typeCode.getId()) {
//					
//				} else {
//					
//				}
				
				//int ruleStatusCode = validateRule(pathValueString, expectedValueString, ruleCode.getId(), statusCode.getId(), clazz);
				
				// NAME: response = 200 OK (TypeCode [id=4, name=STRING] 
				//       RuleCode [id=1, name=EQUALS] StatusCode [id=2, name=RUNNING CORRECTLY] 200 OK true
				
				// NAME: contentLength = 12570 (TypeCode [id=1, name=INTEGER] RuleCode [id=1, name=EQUALS] 
				//       StatusCode [id=2, name=RUNNING CORRECTLY] 12570 true
				
				RuleResult rulezResult = new RuleResult();
				
				rulezResult.setRule(rule);
				rulezResult.setConditionMet(1);
				rulezResult.setReason("Expected " + pathValueString + " to " + ruleCode.getName() + " " + expectedValueString);
				
				//TODO
				System.err.println("Rule Result: " + rulezResult.getReason());
				
				ruleResults.add(rulezResult);
			}
		}
		
		// TODO
		//System.err.println(results);
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
