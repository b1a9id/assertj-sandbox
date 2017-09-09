package com.example.assertjsandbox.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OnExtractedProperties {

	/**
	 * brandsの中に少なくともsneeuwとethosensは含んでいる
	 */
	@Test
	public void brandsContainsSneeuwAndEthosens() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.contains(sneeuw, ethosens);
	}

	/**
	 * brandsの中のnameに少なくともstofとDulcamaraは含んでいる
	 */
	@Test
	public void brandsNamePropertyContainsStofAndDulcamara() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);
		List<String> brandNames = brands.stream()
				.map(Brand::getName)
				.collect(Collectors.toList());

		Assertions.assertThat(brandNames)
				.contains("stof", "Dulcamara");
	}

	/**
	 * brandsのnameプロパティの中に少なくともbedsidedramaを含んでおり、Portailleは含んでいない
	 */
	@Test
	public void brandsNamePropertyContainsBedsidedramaNotContainsPortaille() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.extracting("name")
				.contains("bedsidedrama")
				.doesNotContain("Portaille");
	}

	/**
	 * brandsの中にnameとgenderが一致するオブジェクトを含んでいる
	 */
	@Test
	public void brandsContainsBrandMatchNameAndGenderProperties() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.extracting("name", "gender")
				.contains(
						Assertions.tuple("sneeuw", Gender.WOMAN),
						Assertions.tuple("ETHOSENS", Gender.MAN));
	}

	@Test
	public void brandsContainsBrandMatchNameAndGenderPropertiesAndNotContains() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands)
				.extracting("name", "gender")
				.contains(
						Assertions.tuple("sneeuw", Gender.WOMAN),
						Assertions.tuple("ETHOSENS", Gender.MAN))
				.doesNotContain(
						Assertions.tuple("Portaille", Gender.MAN)
				);
	}
}
