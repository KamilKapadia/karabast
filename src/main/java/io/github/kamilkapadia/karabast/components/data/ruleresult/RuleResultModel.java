package io.github.kamilkapadia.karabast.components.data.ruleresult;

public class RuleResultModel {
	private long id;
	private long resultId;
	private String result;
	private long ruleId;
	private String rule;
	private boolean conditionMet;
	private String reason;
	
	public RuleResultModel(RuleResult ruleResult, String baseUrlString) {
		setId(ruleResult.getId());
		setResultId(ruleResult.getResult().getId());
		setResult(baseUrlString + "/api/results/" + getResultId());
		setRuleId(ruleResult.getRule().getId());
		setRule(baseUrlString + "/api/rules/" + getRuleId());
		setConditionMet(ruleResult.getConditionMet() == 1 ? true : false);
		setReason(ruleResult.getReason());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getResultId() {
		return resultId;
	}
	public void setResultId(long resultId) {
		this.resultId = resultId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public long getRuleId() {
		return ruleId;
	}
	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public boolean isConditionMet() {
		return conditionMet;
	}
	public void setConditionMet(boolean conditionMet) {
		this.conditionMet = conditionMet;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return "RuleResultModel [id=" + id + ", resultId=" + resultId + ", result=" + result + ", ruleId=" + ruleId
				+ ", rule=" + rule + ", conditionMet=" + conditionMet + ", reason=" + reason + "]";
	}
		
}
