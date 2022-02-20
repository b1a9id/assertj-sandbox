package com.example.assertjsandbox.custom;

import java.util.Objects;

import org.assertj.core.api.AbstractAssert;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

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
		if (!Objects.equals(actual.name(), name)) {
			failWithMessage("Expected character's name to be <%s> but was <%s>", name, actual.name());
		}

		return this;
	}

	public BrandAssert hasGender(Gender gender) {
		isNotNull();

		if (actual.gender() != gender) {
			failWithMessage("Expected character's gender to be <%s> but was <%s>", gender, actual.gender());
		}

		return this;
	}
}
