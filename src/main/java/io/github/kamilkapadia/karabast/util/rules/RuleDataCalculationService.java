package io.github.kamilkapadia.karabast.util.rules;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleResult;
import io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleData;

public class RuleDataCalculationService {

    private KieContainer kContainer;

    public RuleDataCalculationService(RuleConfiguration config) {
    	kContainer = config.kieContainer();
    }
    
    public String calculate(DroolsRuleData droolsRuleData, DroolsRuleResult droolsRuleResult) {
    	KieSession kieSession = kContainer.newKieSession();
        kieSession.setGlobal("droolsRuleResult", droolsRuleResult);
        kieSession.insert(droolsRuleData);
        kieSession.fireAllRules();
        kieSession.dispose();

        return droolsRuleResult.toString();
    }
}
