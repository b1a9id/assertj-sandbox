package com.example.assertjsandbox.model;

import org.junit.Test;

public class CustomAssertion {

	@Test
	public void brandAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);

		BrandAssert.assertThat(stof).hasName("stof");
	}
}
