package io.github.kamilkapadia.karabast.rest.lookup.response;

import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;

public class ActionCodeResponse {

	private int id;
	private String name;

	public ActionCodeResponse(ActionCode actionCode) {
		setId(actionCode.getId());
		setName(actionCode.getName());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ActionCodeResponse [id=" + id + ", name=" + name + "]";
	}

}
