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

@Entity
@Table(name = "action")
public class Action {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
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

	public Action() {
		
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

	@Override
	public String toString() {
		return "Action [id=" + id + ", job_id=" + job.getId() + ", typeMask=" + typeMask + ", actionMask=" + actionMask
				+ ", active=" + active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}

