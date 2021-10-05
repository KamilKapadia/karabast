package io.github.kamilkapadia.karabast.rest.lookup.response;

import io.github.kamilkapadia.karabast.dto.lookup.RuleCode;

public class RuleCodeResponse {

	private int id;
//	private int typeMask;
//	private List<TypeCode> types;
	private String name;

	public RuleCodeResponse(RuleCode ruleCode /*, List<TypeCode> allTypeCodes*/) {
		setId(ruleCode.getId());
		setName(ruleCode.getName());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getTypeMask() {
//		return typeMask;
//	}
//
//	public void setTypeMask(int typeMask) {
//		this.typeMask = typeMask;
//	}
//
//	public List<TypeCode> getTypes() {
//		return types;
//	}
//
//	public void setTypes(List<TypeCode> types) {
//		this.types = types;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RuleCodeResponse [id=" + id + ", name=" + name + "]";
	}

	

}
