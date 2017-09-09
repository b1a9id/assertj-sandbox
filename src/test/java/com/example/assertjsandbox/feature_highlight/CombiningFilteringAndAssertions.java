package com.example.assertjsandbox.feature_highlight;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombiningFilteringAndAssertions {

	/**
	 * brandsの中で、nameに「e」を含んでいるものを抽出
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

	/**
	 * brandsの中でgenderがMANのものだけ抽出
	 */
	@Test
	public void filteringMatchPropertyValues() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn("gender", Gender.MAN)
				.containsOnly(stof, bedsidedrama, ethosens);
	}

	/**
	 * brandsの中でgenderがMANでないものだけ抽出
	 */
	@Test
	public void filteringNotMatchPropertyValues() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn("gender", Assertions.not(Gender.MAN))
				.containsOnly(sneeuw, dulcamara);
	}

	/**
	 * brandsの中でnameに"sneeuw", "Dulcamara"を含むものだけ抽出
	 */
	@Test
	public void filteringIncludePropertyValues() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn("name", Assertions.in("sneeuw", "Dulcamara"))
				.containsOnly(dulcamara, sneeuw);
	}

	/**
	 * brandsの中でnameに"sneeuw", "Dulcamara"を含まないものだけ抽出
	 */
	@Test
	public void filteringNoIncludePropertyValues() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn("name", Assertions.notIn("sneeuw", "Dulcamara"))
				.containsOnly(stof, bedsidedrama, ethosens);
	}

	/**
	 * brandsの中でnameに「e」を含んでいてかつ、genderがMANのものを抽出
	 */
	@Test
	public void filteringMatchMultiplePropertiesValue() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn(brand -> brand.getName().contains("e"))
				.filteredOn("gender", Gender.MAN)
				.containsOnly(bedsidedrama);
	}

	/**
	 * Conditionクラスを使用したフィルタリング
	 */
	@Test
	public void filteringWithCondition() {
		Condition<Brand> includeEBrands = new Condition<Brand>() {
			@Override
			public boolean matches(Brand brand) {
				return brand.getName().contains("e");
			}
		};

		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.filteredOn(includeEBrands)
				.containsOnly(bedsidedrama, sneeuw);
	}
}
