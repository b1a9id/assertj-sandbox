package com.example.assertjsandbox.model;


import java.util.Set;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;


class UsingCondition {
	static Set<String> favoriteBrands = Sets.newLinkedHashSet("stof", "bedsidedrama", "portaille");
	Condition<String> favoriteBrandsCondition = new Condition<>(favoriteBrands::contains, "favoriteBrands");

	static Set<String> likeBrands = Sets.newLinkedHashSet("ETHOSENS", "prasthana", "bedsidedrama");
	Condition<String> likeBrandsCondition = new Condition<>(likeBrands::contains, "likeBrands");

	@Test
	void usingIsAndIsNot() {
		Assertions.assertThat("stof").is(favoriteBrandsCondition);
		Assertions.assertThat("storama").isNot(favoriteBrandsCondition);
	}

	@Test
	void usingHasAndHasNot() {
		Assertions.assertThat("stof").has(favoriteBrandsCondition);
		Assertions.assertThat("storama").doesNotHave(favoriteBrandsCondition);
	}

	//TODO:調べる
	@Test
	void verifyOnCollectionElements() {
		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama")).are(favoriteBrandsCondition);
		Assertions.assertThat(Sets.newLinkedHashSet("prasthana", "ETHOSENS")).areNot(favoriteBrandsCondition);

		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama")).have(favoriteBrandsCondition);
		Assertions.assertThat(Sets.newLinkedHashSet("prasthana", "ETHOSENS")).doNotHave(favoriteBrandsCondition);

		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama", "Dulcamara")).areAtLeast(2, favoriteBrandsCondition);
		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama", "Dulcamara")).haveAtLeast(2, favoriteBrandsCondition);

		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama", "Dulcamara")).areAtMost(2, favoriteBrandsCondition);
		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama", "Dulcamara")).haveAtMost(2, favoriteBrandsCondition);

		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama", "Dulcamara")).areExactly(2, favoriteBrandsCondition);
		Assertions.assertThat(Sets.newLinkedHashSet("stof", "bedsidedrama", "Dulcamara")).haveExactly(2, favoriteBrandsCondition);
	}

	@Test
	void combineConditions() {
		Assertions.assertThat("ETHOSENS").is(Assertions.anyOf(favoriteBrandsCondition, likeBrandsCondition));
		Assertions.assertThat("bedsidedrama").is(Assertions.allOf(favoriteBrandsCondition, likeBrandsCondition));
	}
}
