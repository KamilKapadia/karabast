package io.github.kamilkapadia.karabast.util.rules.model;

import java.util.HashMap;
import java.util.Map;

public class DroolsRuleData {
    private Map<String, Boolean> booleanMap = new HashMap<>();
    private Map<String, Long> longMap = new HashMap<>();
    
    public Boolean getBooleanValue(String key) {
    	return booleanMap.get(key);
    }

    public void setBooleanValue(String key, Boolean value) {
    	booleanMap.put(key, value);
    }

    public Long getLongValue(String key) {
    	return longMap.get(key);
    }

    public void setLongValue(String key, Long value) {
    	longMap.put(key, value);
    }
    
	@Override
	public String toString() {
		return "RuleData [booleanMap=" + booleanMap + ", longMap=" + longMap + "]";
	}
}
