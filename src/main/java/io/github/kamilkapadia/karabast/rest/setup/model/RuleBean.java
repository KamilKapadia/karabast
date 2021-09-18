package io.github.kamilkapadia.karabast.rest.setup.model;

import java.util.Date;

import io.github.kamilkapadia.karabast.dto.setup.Rule;

public class RuleBean {
	private long id;
	private long jobId;
	private String name;
	private String valuePath;
	private String rule;
	private boolean active;
	private Date creationTime;
	private Date lastUpdateTime;
	
	public RuleBean(Rule rule) {
		setId(rule.getId());
		setJobId(rule.getJob().getId());
		setName(rule.getName());
		setValuePath(rule.getValuePath());
		setRule(rule.getTypeCode().getName() + " " + rule.getRuleCode().getName() + " '" + rule.getExpectedValue() + "' : " + rule.getStatusCode().getName());
		setActive(rule.isActive());
		setCreationTime(rule.getCreationTime());
		setLastUpdateTime(rule.getLastUpdateTime());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValuePath() {
		return valuePath;
	}
	public void setValuePath(String valuePath) {
		this.valuePath = valuePath;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Override
	public String toString() {
		return "RuleBean [id=" + id + ", jobId=" + jobId + ", name=" + name + ", valuePath=" + valuePath + ", rule="
				+ rule + ", active=" + active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime
				+ "]";
	}
}