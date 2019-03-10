package com.example.assertjsandbox.collection;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class StandardAssertionTest {

	/**
	 * リストであることの検証
	 */
	@Test
	void asList() {
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
	void listAssertion () {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, ethosens);

		Assertions.assertThat(brands)
				.extracting(Brand::getName)
				.containsExactly("stof", "bedsidedrama", "ETHOSENS");
	}

	/**
	 * 全フィールドがnullであることの検証
	 */
	@Test
	void hasAllNullFieldsOrPropertiesAssertion() {
		Brand ethosens = new Brand(null, null, null);
		Assertions.assertThat(ethosens)
				.hasAllNullFieldsOrProperties();
		Assertions.assertThat(ethosens)
				.hasAllNullFieldsOrPropertiesExcept();

		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", null);
		Assertions.assertThat(bedsidedrama)
				.hasAllNullFieldsOrPropertiesExcept("name", "designer");
	}

	/**
	 * Listサイズの検証
	 */
	@Test
	void hasSizeAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		List<Brand> brands = List.of(stof, bedsidedrama, ethosens);

		Assertions.assertThat(brands)
				.hasSizeGreaterThan(1)
				.hasSizeLessThan(4)
				.hasSizeGreaterThanOrEqualTo(3)
				.hasSizeLessThanOrEqualTo(3)
				.hasSizeBetween(1,4);
	}

	/**
	 * Mapに含まれる全要素が一致することの検証
	 */
	@Test
	void containsExactlyEntriesOfAssertion() {
		Map<Integer, String> map1 = Map.of(1, "TEST1", 2, "TEST2");
		Map<Integer, String> map2 = Map.of(1, "TEST1", 2, "TEST2");
		Assertions.assertThat(map1).containsAllEntriesOf(map2);
	}

	/**
	 * 指定したkeyのみ含まれていること
	 */
	@Test
	void containsOnlyKeysAssertion() {
		Map<Integer, String> map1 = Map.of(1, "TEST1", 2, "TEST2");
		Assertions.assertThat(map1).containsOnlyKeys(List.of(1, 2));
	}
}
