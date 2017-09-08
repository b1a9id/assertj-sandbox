package com.example.assertjsandbox.feature_highlight;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombiningFilteringAndAssertions {

	/**
	 * リストの中で、nameに「e」を含んでいるものを抽出
	 */
	@Test
	public void filteringPredicate() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);

		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn(brand -> brand.getName().contains("e"))
				.containsOnly(bedsidedrama, sneeuw);
	}


}
