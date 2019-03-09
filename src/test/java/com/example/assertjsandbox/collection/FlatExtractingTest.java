package com.example.assertjsandbox.collection;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Gender;
import com.example.assertjsandbox.model.InitialCharBrand;

class FlatExtractingTest {

	/**
	 * brandsのbrandNameプロパティに「stof」「storama」「prasthana」「protaille」を含んでいる
	 */
	@Test
	void flatExtracting() {
		InitialCharBrand stof = new InitialCharBrand("stof", "Tanita", Gender.MAN);
		InitialCharBrand storama = new InitialCharBrand("storama", "Tanita", Gender.MAN);
		InitialCharBrand initialSBrand = new InitialCharBrand();
		initialSBrand.setInitialCharBrands(Arrays.asList(stof, storama));

		InitialCharBrand prasthana = new InitialCharBrand("prasthana", "Takei", Gender.MAN);
		InitialCharBrand portaille = new InitialCharBrand("portaille", "Oobuchi", Gender.MAN);
		InitialCharBrand initialPBrand = new InitialCharBrand();
		initialPBrand.setInitialCharBrands(Arrays.asList(prasthana, portaille));
		List<InitialCharBrand> brands = Arrays.asList(initialSBrand, initialPBrand);

		Assertions.assertThat(brands)
				.flatExtracting("initialCharBrands")
				.containsExactly(stof, storama, prasthana, portaille);
	}
}
