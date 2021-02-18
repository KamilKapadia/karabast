package io.github.kamilkapadia.karabast.dto.setup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "tier")
	private String tier;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "cluster")
	private String cluster;
	
	@Column(name = "server")
	private String server;
	
	@Column(name = "application")
	private String application;
	
	@Column(name = "target_url")
	private String targetUrl;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;
	
	// Deleting a job should wipe out all things associated to that job id
	@OneToMany(fetch=FetchType.LAZY, mappedBy="job", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<HistoricalName> historicalNames = new ArrayList<HistoricalName>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="job", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ContentPath> contentPaths = new ArrayList<ContentPath>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="job", cascade = {CascadeType.ALL})
    private List<Rule> rules = new ArrayList<Rule>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="job", cascade = {CascadeType.ALL})
    private List<Action> actions = new ArrayList<Action>();
	
	public Job() {
		
	}
		
	public Job(String name, String tier, String location, String cluster, String server, String application,
			String targetUrl, boolean active) {
		
		this.name = name;
		this.tier = tier;
		this.location = location;
		this.cluster = cluster;
		this.server = server;
		this.application = application;
		this.targetUrl = targetUrl;
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

	public void add(HistoricalName historicalName) {
		historicalNames.add(historicalName);
		historicalName.setJob(this);
	}
	
	public void add(ContentPath path) {
		contentPaths.add(path);
		path.setJob(this);
	}
	
	public void add(Rule rule) {
		rules.add(rule);
		rule.setJob(this);
	}
	
	public void add(Action action) {
		actions.add(action);
		action.setJob(this);
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", tier=" + tier + ", location=" + location + ", cluster=" + cluster
				+ ", server=" + server + ", application=" + application + ", targetUrl=" + targetUrl + ", active="
				+ active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", historicalNames=" + historicalNames + ", contentPaths=" + contentPaths + ", rules=" + rules
				+ ", actions=" + actions + "]";
	}
}

