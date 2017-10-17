package com.example.assertjsandbox.collection;

import com.example.assertjsandbox.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;

public class StandardAssertion {

	/**
	 * リストであることの検証
	 */
	@Test
	public void asList() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands).asList();
	}

	/**
	 * 特定のプロパティの検証
	 */
	@Test
	public void listAssertion () {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, ethosens);

		Assertions.assertThat(brands)
				.extracting(Brand::getName)
				.containsExactly("stof", "bedsidedrama", "ETHOSENS");
	}
}
