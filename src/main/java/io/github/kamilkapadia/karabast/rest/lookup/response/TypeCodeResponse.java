package io.github.kamilkapadia.karabast.rest.lookup.response;

import io.github.kamilkapadia.karabast.dto.lookup.TypeCode;

public class TypeCodeResponse {

	private int id;
	private String name;

	public TypeCodeResponse(TypeCode typeCode) { 
		setId(typeCode.getId());
		setName(typeCode.getName());
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
		return "TypeCodeResponse [id=" + id + ", name=" + name + "]";
	}
}
