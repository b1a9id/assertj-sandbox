package com.example.assertjsandbox.model;

public class Brand {
	private String name;

	private String designer;

	private Gender gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Brand() {}

	public Brand(String name, String designer, Gender gender) {
		this.name = name;
		this.designer = designer;
		this.gender = gender;
	}

	public String getNameInitial() {
		return getName().substring(0, 1);
	}
}
