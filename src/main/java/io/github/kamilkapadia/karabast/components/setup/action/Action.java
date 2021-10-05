package io.github.kamilkapadia.karabast.components.setup.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.github.kamilkapadia.karabast.components.lookup.actioncode.ActionCode;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCode;
import io.github.kamilkapadia.karabast.components.setup.job.Job;

@Entity
@Table(name = "action")
public class Action {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name="job_id")
	private Job job;
	
	@Column(name = "type_mask")
	private int typeMask;
	
	@Column(name = "action_mask")
	private int actionMask;
		
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "creation_time")
	private Timestamp creationTime;
	
	@Column(name = "last_update_time")
	private Timestamp lastUpdateTime;

	@Transient
	private List<StatusCode> statuses = new ArrayList<StatusCode>();
	
	@Transient
	private List<ActionCode> actions = new ArrayList<ActionCode>();
	
	public Action() {
		this.active = true;
		this.creationTime = new Timestamp(System.currentTimeMillis());
		this.lastUpdateTime = new Timestamp(System.currentTimeMillis());
	}

	
	
	public Action(int typeMask, int actionMask, boolean active) {
		super();
		this.typeMask = typeMask;
		this.actionMask = actionMask;
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getTypeMask() {
		return typeMask;
	}

	public void setTypeMask(int typeMask) {
		this.typeMask = typeMask;
	}

	public int getActionMask() {
		return actionMask;
	}

	public void setActionMask(int actionMask) {
		this.actionMask = actionMask;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreationTime() {
		return (Timestamp)creationTime.clone();
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = (Timestamp)creationTime.clone();
	}

	public Timestamp getLastUpdateTime() {
		return (Timestamp)lastUpdateTime.clone();
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = (Timestamp)lastUpdateTime.clone();
	}

	public List<StatusCode> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<StatusCode> statuses) {
		this.statuses = statuses;
		
		int mask = 0;
		
		for (StatusCode statusCode : this.statuses) {
			mask = mask | statusCode.getId();
		}
		
		setTypeMask(mask);
	}

	public void setStatuses(int[] statusCodes) {
		int mask = 0;
		
		for (int statusCode : statusCodes) {
			mask = mask | statusCode;
		}
		
		setTypeMask(mask);
	}


	public List<ActionCode> getActions() {
		return actions;
	}



	public void setActions(List<ActionCode> actions) {
		this.actions = actions;
		
		int mask = 0;
		
		for (ActionCode actionCode : this.actions) {
			mask = mask | actionCode.getId();
		}
		
		setActionMask(mask);
	}



	@Override
	public String toString() {
		return "Action [id=" + id + ", job=" + job + ", typeMask=" + typeMask + ", actionMask=" + actionMask
				+ ", active=" + active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", statuses=" + statuses + ", actions=" + actions + "]";
	}
}
