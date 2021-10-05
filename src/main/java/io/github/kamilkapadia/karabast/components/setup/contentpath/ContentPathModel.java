package io.github.kamilkapadia.karabast.components.setup.contentpath;

import java.util.Date;

public class ContentPathModel {
	private long id;
	private long jobId;
	private String job;
	private String name;
	private String contentDiskDir;
	private boolean active;
	private Date creationTime;
	private Date lastUpdateTime;
	
	public ContentPathModel(ContentPath contentPath, String baseUrlString) {
		setId(contentPath.getId());
		setJobId(contentPath.getJob().getId());
		setJob(baseUrlString + "/api/jobs/" + getJobId());
		setName(contentPath.getName());
		setContentDiskDir(contentPath.getDiskDir());
		setActive(contentPath.isActive());
		setCreationTime(contentPath.getCreationTime());
		setLastUpdateTime(contentPath.getLastUpdateTime());
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

	public String getContentDiskDir() {
		return contentDiskDir;
	}

	public void setContentDiskDir(String contentDiskDir) {
		this.contentDiskDir = contentDiskDir;
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
		return "ContentPathResponse [id=" + id + ", jobId=" + jobId + ", job=" + job + ", name=" + name
				+ ", contentDiskDir=" + contentDiskDir + ", active=" + active + ", creationTime=" + creationTime
				+ ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
