package com.example.assertjsandbox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InitialCharBrand {
	public InitialCharBrand(String name, String designer, Gender gender) {
		this.name = name;
		this.designer = designer;
		this.gender = gender;
	}

	private String name;

	private String designer;

	private Gender gender;

	private List<InitialCharBrand> initialCharBrands;
}
