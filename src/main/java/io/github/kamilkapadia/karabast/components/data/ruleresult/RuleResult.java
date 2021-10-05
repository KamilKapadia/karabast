package io.github.kamilkapadia.karabast.components.data.ruleresult;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.components.data.result.Result;
import io.github.kamilkapadia.karabast.components.setup.rule.Rule;


@Entity
@Table(name = "rule_result")
public class RuleResult {

	/*
	id LONG PRIMARY KEY auto_increment NOT NULL,
	result_id LONG NOT NULL, 
	rule_id LONG NOT NULL, 
	condition_met int NOT NULL, 
	reason VARCHAR(256) NOT NULL);
	*/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="result_id")
    private Result result;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="rule_id")
	private Rule rule;
	
	@Column(name = "condition_met")
	private int conditionMet;
	
	@Column(name = "reason")
	private String reason;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public int getConditionMet() {
		return conditionMet;
	}

	public void setConditionMet(int conditionMet) {
		this.conditionMet = conditionMet;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
