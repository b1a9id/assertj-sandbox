package com.example.assertjsandbox.collection;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

class ResultsOfAMethodCallTest {

	/**
	 * brandsの中からBrand#getNameInitialの結果が一致するnameを抽出
	 */
	@Test
	void extractingResultOf() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.extractingResultOf("getNameInitial")
				.contains("s", "b", "E", "D");
	}
}
