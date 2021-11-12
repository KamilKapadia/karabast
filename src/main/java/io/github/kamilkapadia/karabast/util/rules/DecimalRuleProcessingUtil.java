package io.github.kamilkapadia.karabast.util.rules;

import java.util.Arrays;

public class DecimalRuleProcessingUtil {

		public static int validate(double value, double expectedValue, int passedCode, int failedCode, int ruleCheck) {
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
	

	
	public static int processSimpleRule(double value, double expectedValue, int passedCode, int failedCode,
			int ruleCheck) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int processBetweenRule(double value, double lowExpectedValue, double highExpectedValue, int passedCode, int failedCode, int ruleCheck) {
		switch (ruleCheck) {
		case RulesProcessingUtil.BETWEEN:
			return (value > lowExpectedValue && value < highExpectedValue) ? passedCode : failedCode;
		case RulesProcessingUtil.NOT_BETWEEN:
			return (value > lowExpectedValue && value < highExpectedValue) ? failedCode : passedCode;
		default:
			return failedCode;
		}
	}
	
	public static int processInRule(double value, double[] expectedValues, int passedCode, int failedCode, int ruleCheck) {
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
