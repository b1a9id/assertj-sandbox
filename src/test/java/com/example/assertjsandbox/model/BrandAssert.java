package com.example.assertjsandbox.model;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class BrandAssert extends AbstractAssert<BrandAssert, Brand>{

	public BrandAssert(Brand brand) {
		super(brand, BrandAssert.class);
	}

	public static BrandAssert assertThat(Brand actual) {
		return new BrandAssert(actual);
	}

	@Override
	public BrandAssert isNotNull() {
		return super.isNotNull();
	}

	public BrandAssert hasName(String name) {
		isNotNull();
		if (!Objects.equals(actual.getName(), name)) {
			failWithMessage("Expected character's name to be <%s> but was <%s>", name, actual.getName());
		}

		return this;
	}

	public BrandAssert hasGender(Gender gender) {
		isNotNull();

		if (actual.getGender() != gender) {
			failWithMessage("Expected character's gender to be <%s> but was <%s>", gender, actual.getGender());
		}

		return this;
	}
}
