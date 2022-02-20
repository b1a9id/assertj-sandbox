package com.example.assertjsandbox.model;

public record Brand(String name, String designer, Gender gender) {

	public String getNameInitial() {
		return name().substring(0, 1);
	}
}
