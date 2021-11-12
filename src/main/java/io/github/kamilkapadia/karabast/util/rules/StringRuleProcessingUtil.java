package io.github.kamilkapadia.karabast.util.rules;

import java.util.Arrays;

public class StringRuleProcessingUtil {

	public static int validate(String value, String expectedValue, int passedCode, int failedCode, int ruleCheck) {
		switch (ruleCheck) {
		case RulesProcessingUtil.BETWEEN:
		case RulesProcessingUtil.NOT_BETWEEN:
			return processBetweenRule(value, expectedValue.split(",")[0], expectedValue.split(",")[1], passedCode,
					failedCode, ruleCheck);
		case RulesProcessingUtil.IS_IN:
		case RulesProcessingUtil.IS_NOT_IN:
			return processInRule(value, expectedValue.split(","), passedCode, failedCode, ruleCheck);
		default:
			return processSimpleRule(value, expectedValue, passedCode, failedCode, ruleCheck);
		}
	}

	public static int processSimpleRule(String value, String expectedValue, int passedCode, int failedCode,
			int ruleCheck) {

		switch (ruleCheck) {
		case RulesProcessingUtil.EQUALS:
			return value.equals(expectedValue) ? passedCode : failedCode;
		case RulesProcessingUtil.NOT_EQUALS:
			return !value.equals(expectedValue) ? passedCode : failedCode;
		case RulesProcessingUtil.GREATER_THAN:
			return value.compareTo(expectedValue) > 0 ? passedCode : failedCode;
		case RulesProcessingUtil.LESS_THAN:
			return value.compareTo(expectedValue) < 0 ? passedCode : failedCode;
		case RulesProcessingUtil.GREATER_THAN_OR_EQUALS:
			return value.compareTo(expectedValue) >= 0 ? passedCode : failedCode;
		case RulesProcessingUtil.LESS_THAN_OR_EQUALS:
			return value.compareTo(expectedValue) <= 0 ? passedCode : failedCode;
		case RulesProcessingUtil.STARTS_WITH:
			return value.startsWith(expectedValue) ? passedCode : failedCode;
		case RulesProcessingUtil.ENDS_WITH:
			return value.endsWith(expectedValue) ? passedCode : failedCode;
		case RulesProcessingUtil.CONTAINS:
			return value.contains(expectedValue) ? passedCode : failedCode;
		default:
			return failedCode;
		}
	}

	public static int processBetweenRule(String value, String lowExpectedValue, String highExpectedValue,
			int passedCode, int failedCode, int ruleCheck) {
		switch (ruleCheck) {
		case RulesProcessingUtil.BETWEEN:
			return ((value.compareTo(lowExpectedValue) > 0) && (value.compareTo(highExpectedValue) < 0)) ? passedCode
					: failedCode;
		case RulesProcessingUtil.NOT_BETWEEN:
			return ((value.compareTo(lowExpectedValue) > 0) && (value.compareTo(highExpectedValue) < 0)) ? failedCode
					: passedCode;
		default:
			return failedCode;
		}
	}

	public static int processInRule(String value, String[] expectedValues, int passedCode, int failedCode,
			int ruleCheck) {
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
