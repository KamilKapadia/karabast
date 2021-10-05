package io.github.kamilkapadia.karabast.components.lookup.actioncode;

public class ActionCodeModel {

	private int id;
	private String name;

	public ActionCodeModel(ActionCode actionCode) {
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
