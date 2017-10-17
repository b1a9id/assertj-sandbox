package com.example.assertjsandbox.collection;

import com.example.assertjsandbox.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ResultsOfAMethodCall {

	/**
	 * brandsの中からBrand#getNameInitialの結果が一致するnameを抽出
	 */
	@Test
	public void extractingResultOf() {
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
