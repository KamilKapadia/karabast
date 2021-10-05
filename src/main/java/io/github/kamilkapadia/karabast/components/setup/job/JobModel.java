package io.github.kamilkapadia.karabast.components.setup.job;

import java.util.Date;

public class JobModel {
	private long id;
	private String name;
	private String tier;
	private String location;
	private String cluster;
	private String server;
	private String application;
	private String targetUrl;
	private boolean active;
	private Date creationTime;
	private Date lastUpdateTime;

	public JobModel(Job job, String baseUrlString) {
		setId(job.getId());
		setName(job.getName());
		setTier(job.getTier());
		setLocation(job.getLocation());
		setCluster(job.getCluster());
		setServer(job.getServer());
		setApplication(job.getApplication());
		setTargetUrl(job.getTargetUrl());
		setActive(job.isActive());
		setCreationTime(job.getCreationTime());
		setLastUpdateTime(job.getLastUpdateTime());
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

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
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
		return "JobResponse [id=" + id + ", name=" + name + ", tier=" + tier + ", location=" + location + ", cluster="
				+ cluster + ", server=" + server + ", application=" + application + ", targetUrl=" + targetUrl
				+ ", active=" + active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}

	
}
