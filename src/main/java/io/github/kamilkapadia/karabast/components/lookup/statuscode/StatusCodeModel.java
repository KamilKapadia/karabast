package io.github.kamilkapadia.karabast.components.lookup.statuscode;

public class StatusCodeModel {

	private int id;
	private String name;

	public StatusCodeModel(StatusCode statusCode) {
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
