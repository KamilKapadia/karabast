package io.github.kamilkapadia.karabast.rest.lookup.response;

import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;

public class StatusCodeResponse {

	private int id;
	private String name;

	public StatusCodeResponse(StatusCode statusCode) {
		setId(statusCode.getId());
		setName(statusCode.getName());
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
		return "StatusCodeResponse [id=" + id + ", name=" + name + "]";
	}
}
