package com.example.assertjsandbox.custom;

import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

class CustomAssertion {

	@Test
	void brandAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);

		BrandAssert.assertThat(stof)
				.hasName("stof")
				.hasGender(Gender.MAN);
	}
}
