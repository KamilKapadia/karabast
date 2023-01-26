package io.github.kamilkapadia.karabast.util.rules.model;

import java.util.HashMap;
import java.util.Map;

public class DroolsRuleResult {

	private Map<String, Boolean> results = new HashMap<>();

    public DroolsRuleResult(String ...keys) {
    	results = new HashMap<>();
    	
    	for (String key : keys) {
    		results.put(key, false);
    	}
    }

	public Map<String, Boolean> getResults() {
		return results;
	}

	public void setResults(Map<String, Boolean> results) {
		this.results = results;
	}

	public void addResult(String key, Boolean value) {
		results.put(key, value);
	}

	@Override
	public String toString() {
		return "RuleResult [results=" + results + "]";
	}
}