package io.github.kamilkapadia.karabast.dto.setup;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.dto.lookup.RuleCode;
import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;
import io.github.kamilkapadia.karabast.dto.lookup.TypeCode;


@Entity
@Table(name = "rule")
public class Rule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value_path")
	private String valuePath;
	
	@Column(name = "expected_value")
	private String expectedValue;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;

	@ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="job_id")
	private Job job; 
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="status_type_code")
	private StatusCode statusCode; 
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "type_code_id")
	private TypeCode typeCode; 
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "rule_code")
	private RuleCode ruleCode;
	
	public Rule() {
		
	}
	
	public Rule(String name, String valuePath, String expectedValue, StatusCode statusCode, TypeCode typeCode, 
			RuleCode ruleCode, boolean active) {
		
		super();
		this.name = name;
		this.valuePath = valuePath;
		this.expectedValue = expectedValue;
		this.statusCode = statusCode;
		this.typeCode = typeCode;
		this.ruleCode = ruleCode;
		this.active = active;
		this.creationTime = new Timestamp(System.currentTimeMillis());
		this.lastUpdateTime = new Timestamp(System.currentTimeMillis());
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public TypeCode getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(TypeCode typeCode) {
		this.typeCode = typeCode;
	}

	public RuleCode getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(RuleCode ruleCode) {
		this.ruleCode = ruleCode;
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", name=" + name + ", valuePath=" + valuePath + ", expectedValue=" + expectedValue
				+ ", active=" + active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", job_id=" + job.getId() + ", statusCode=" + statusCode + ", typeCode=" + typeCode + ", "
				+ "ruleCode=" + ruleCode + "]";
	} 
}
