package io.github.kamilkapadia.karabast.util.rules;

import java.io.StringReader;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;

public class RuleConfiguration {

	private String ruleString = 
			"import io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleData;\r\n" + 
    		"import io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleResult;\r\n" + 
    		"import java.util.*;\r\n" + 
    		"\r\n" + 
    		"global io.github.kamilkapadia.karabast.util.rules.model.DroolsRuleResult droolsRuleResult;\r\n" + 
    		"dialect  \"mvel\"\r\n" + 
    		"\r\n";

    public RuleConfiguration(List<String> ruleStrings) {
    	for (int i = 0; i < ruleStrings.size(); i++) {
    		ruleString = ruleString + ruleStrings.get(i);	
    	}
    	
    	System.out.println(ruleString);
    }
    
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        
        kieFileSystem.write( "src/main/resources/simple.drl",
            kieServices.getResources().newReaderResource( new StringReader(ruleString) ) );
        
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
