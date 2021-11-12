package io.github.kamilkapadia.karabast.util.rules;

import java.util.Arrays;

public class IntegerRuleProcessingUtil {

	public static int validate(int value, int expectedValue, int passedCode, int failedCode, int ruleCheck) {
		
//		switch (ruleCheck) {
//		case BETWEEN:
//		case NOT_BETWEEN:
//			return processBetweenRule(value, expectedValue.split(",")[0],expectedValue.split(",")[1], passedCode, failedCode, ruleCheck);
//		case IS_IN:
//		case IS_NOT_IN:	
//			return processInRule(value, expectedValue.split(","), passedCode, failedCode, ruleCheck);
//		default:
			return processSimpleRule(value, expectedValue, passedCode, failedCode, ruleCheck);
	}

	public static int processSimpleRule(int value, int expectedValue, int passedCode, int failedCode, int ruleCheck) {

		String valueStr = String.valueOf(value);
		String expectedStr = String.valueOf(expectedValue);
		
		switch (ruleCheck) {
		case RulesProcessingUtil.EQUALS:
			return value == expectedValue ? passedCode : failedCode;
		case RulesProcessingUtil.NOT_EQUALS:
			return value != expectedValue ? passedCode : failedCode;
		case RulesProcessingUtil.GREATER_THAN:
			return value > expectedValue ? passedCode : failedCode;
		case RulesProcessingUtil.LESS_THAN:
			return value < expectedValue ? passedCode : failedCode;
		case RulesProcessingUtil.GREATER_THAN_OR_EQUALS:
			return value >= expectedValue ? passedCode : failedCode;
		case RulesProcessingUtil.LESS_THAN_OR_EQUALS:
			return value <= expectedValue ? passedCode : failedCode;
		case RulesProcessingUtil.STARTS_WITH:
			return valueStr.startsWith(expectedStr) ? passedCode : failedCode;
		case RulesProcessingUtil.ENDS_WITH:
			return valueStr.endsWith(expectedStr) ? passedCode : failedCode;
		case RulesProcessingUtil.CONTAINS:
			return valueStr.contains(expectedStr) ? passedCode : failedCode;
		default:
			return failedCode;
		}
	}
	
	public static int processBetweenRule(int value, int lowExpectedValue, int highExpectedValue, int passedCode, int failedCode, int ruleCheck) {
		switch (ruleCheck) {
		case RulesProcessingUtil.BETWEEN:
			return (value > lowExpectedValue && value < highExpectedValue) ? passedCode : failedCode;
		case RulesProcessingUtil.NOT_BETWEEN:
			return (value > lowExpectedValue && value < highExpectedValue) ? failedCode : passedCode;
		default:
			return failedCode;
		}
	}
	
	public static int processInRule(int value, int[] expectedValues, int passedCode, int failedCode, int ruleCheck) {
		Arrays.sort(expectedValues);
		
		switch (ruleCheck) {
		case RulesProcessingUtil.IS_IN:
			return Arrays.binarySearch(expectedValues, value) >= 0 ? passedCode : failedCode;
		case RulesProcessingUtil.IS_NOT_IN:
			return Arrays.binarySearch(expectedValues, value) < 0 ? passedCode : failedCode;
		default:
			return failedCode;
		}
	}

}
