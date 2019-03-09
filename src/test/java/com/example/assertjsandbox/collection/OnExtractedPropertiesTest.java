package com.example.assertjsandbox.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

class OnExtractedPropertiesTest {

	/**
	 * brandsの中に少なくともsneeuwとethosensは含んでいる
	 */
	@Test
	void brandsContainsSneeuwAndEthosens() {
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
	void brandsNamePropertyContainsStofAndDulcamara() {
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
	void brandsNamePropertyContainsBedsidedramaNotContainsPortaille() {
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
	void brandsContainsBrandMatchNameAndGenderProperties() {
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
	void brandsContainsBrandMatchNameAndGenderPropertiesAndNotContains() {
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

	/**
	 * Tuple#tupleを使用した複数プロパティの検証
	 */
	@Test
	void multiPropertiesExtractingForTuple() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		List<Brand> brands = Arrays.asList(stof, ethosens);

		Assertions.assertThat(brands)
				.extracting(Brand::getName, Brand::getGender)
				.containsExactly(
						Tuple.tuple("stof", Gender.MAN),
						Tuple.tuple("ETHOSENS", Gender.MAN)
				);
	}

	@Test
	void multiPropertiesExtractingForFlatExtracting() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		List<Brand> brands = Arrays.asList(stof, ethosens);

		Assertions.assertThat(brands)
				.flatExtracting(Brand::getName, Brand::getGender)
				.containsExactly("stof", Gender.MAN, "ETHOSENS", Gender.MAN);
	}
}
