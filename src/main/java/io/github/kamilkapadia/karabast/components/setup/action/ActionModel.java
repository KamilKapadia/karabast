package io.github.kamilkapadia.karabast.components.setup.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.kamilkapadia.karabast.components.lookup.actioncode.ActionCode;
import io.github.kamilkapadia.karabast.components.lookup.statuscode.StatusCode;

public class ActionModel {

	private long id;
	private long jobId;
	private String job;
	private int statusMask;
	private List<StatusCode> statuses;
	private int actionMask;
	private List<ActionCode> actions;
	private boolean active;
	private Date creationTime;
	private Date lastUpdateTime;
	
	public ActionModel(Action action, List<StatusCode> allStatusCodes, List<ActionCode> allActionCodes, String baseUrlString) {
		setId(action.getId());
		setJobId(action.getJob().getId());
		setJob(baseUrlString + "/api/jobs/" + getJobId());
		setStatusMask(action.getStatusMask());
		setActionMask(action.getActionMask());
		setActive(action.isActive());
		setCreationTime(action.getCreationTime());
		setLastUpdateTime(action.getLastUpdateTime());
		
		List<StatusCode> statusCodes = new ArrayList<StatusCode>();
		
		for (StatusCode statusCode : allStatusCodes) {
			if ( (action.getStatusMask() & statusCode.getId()) > 0) {
				statusCodes.add(statusCode);
			}
		}
			
		setStatuses(statusCodes);
		
		
		List<ActionCode> actionCodes = new ArrayList<ActionCode>();
		
		for (ActionCode actionCode : allActionCodes) {
			if ( (action.getActionMask() & actionCode.getId()) > 0) {
				actionCodes.add(actionCode);
			}
		}
			
		setActions(actionCodes);
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

	public int getStatusMask() {
		return statusMask;
	}

	public void setStatusMask(int statusMask) {
		this.statusMask = statusMask;
	}

	public List<StatusCode> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<StatusCode> statuses) {
		this.statuses = statuses;
	}

	public int getActionMask() {
		return actionMask;
	}

	public void setActionMask(int actionMask) {
		this.actionMask = actionMask;
	}

	public List<ActionCode> getActions() {
		return actions;
	}

	public void setActions(List<ActionCode> list) {
		this.actions = list;
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
		return "ActionResponse [id=" + id + ", jobId=" + jobId + ", job=" + job + ", statusMask=" + statusMask
				+ ", statuses=" + statuses + ", actionMask=" + actionMask + ", actions=" + actions + ", active="
				+ active + ", creationTime=" + creationTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
