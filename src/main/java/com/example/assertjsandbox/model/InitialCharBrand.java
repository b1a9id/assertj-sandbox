package com.example.assertjsandbox.model;

import java.util.List;

public class InitialCharBrand {
	public InitialCharBrand() {}

	public InitialCharBrand(String name, String designer, Gender gender) {
		this.name = name;
		this.designer = designer;
		this.gender = gender;
	}

	private String name;

	private String designer;

	private Gender gender;

	private List<InitialCharBrand> initialCharBrands;

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

	public List<InitialCharBrand> getInitialCharBrands() {
		return initialCharBrands;
	}

	public void setInitialCharBrands(List<InitialCharBrand> initialCharBrands) {
		this.initialCharBrands = initialCharBrands;
	}
}
