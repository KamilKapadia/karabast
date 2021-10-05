package io.github.kamilkapadia.karabast.components.setup.historicalname;

import java.util.Date;

import io.github.kamilkapadia.karabast.components.lookup.typecode.TypeCode;

public class HistoricalNameModel {
	private long id;
	private long jobId;
	private String job;
	private String name;
	private String valuePath;
	private boolean active;
	private Date creationTime;
	private Date lastUpdateTime;
	private TypeCode type;

	public HistoricalNameModel(HistoricalName historicalName, String baseUrlString) {
		setId(historicalName.getId());
		setJobId(historicalName.getJob().getId());
		setJob(baseUrlString + "/api/jobs/" + getJobId());
		setName(historicalName.getName());
		setValuePath(historicalName.getValuePath());
		setActive(historicalName.isActive());
		setCreationTime(historicalName.getCreationTime());
		setLastUpdateTime(historicalName.getLastUpdateTime());
		setType(historicalName.getTypeCode());
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public TypeCode getType() {
		return type;
	}

	public void setType(TypeCode type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "HistoricalNameResponse [id=" + id + ", jobId=" + jobId + ", job=" + job + ", name=" + name
				+ ", valuePath=" + valuePath + ", active=" + active + ", creationTime=" + creationTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", type=" + type + "]";
	}
}
