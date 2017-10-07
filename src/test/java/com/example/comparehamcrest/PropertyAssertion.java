package com.example.comparehamcrest;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class PropertyAssertion {

	@Test
	public void hamcrestAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Assert.assertThat(stof.getName(), is("stof"));
		Assert.assertThat(stof.getDesigner(), is("Tanita"));
		Assert.assertThat(stof.getGender(), is(Gender.MAN));
	}

	@Test
	public void assertjAssertion1() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Assertions.assertThat(stof)
				.hasFieldOrPropertyWithValue("name", "stof")
				.hasFieldOrPropertyWithValue("designer", "Tanita")
				.hasFieldOrPropertyWithValue("gender", Gender.MAN);
	}

	@Test
	public void assertjAssertion2() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Assertions.assertThat(stof)
				.extracting(Brand::getName, Brand::getDesigner, Brand::getGender)
				.containsExactly("stof", "Tanita", Gender.MAN);
	}
}
