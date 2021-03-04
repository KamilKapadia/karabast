package io.github.kamilkapadia.karabast.dto;

import java.util.ArrayList;
import java.util.List;

public class ActionDTO {
	private long id;
	private List<String> statuses = new ArrayList<String>();
	private List<String> actions = new ArrayList<String>();
	private boolean active;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<String> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}
	public List<String> getActions() {
		return actions;
	}
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
