package com.example.assertjsandbox.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Address;
import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import com.example.assertjsandbox.model.Person;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.InstanceOfAssertFactories.STRING;

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
				.extracting(Brand::name)
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

	/**
	 * Iterableが１要素かどうかの検証
	 */
	@Test
	void singleElement() {
		List<String> brandNames = List.of("stof");
		Assertions.assertThat(brandNames).singleElement().isEqualTo("stof");
		Assertions.assertThat(brandNames).singleElement(as(STRING)).startsWith("st");

		List<Brand> brands =
				List.of(new Brand("stof", "Tanita", Gender.MAN));
		Assertions.assertThat(brands)
				.singleElement(as(new InstanceOfAssertFactory<>(Brand.class, Assertions::assertThat)))
				.extracting(Brand::name, Brand::designer, Brand::gender)
				.containsExactly("stof", "Tanita", Gender.MAN);
	}

	/**
	 * Assertions#ssertWithを利用すると、検証のために何度もインスタンスを取得せずに済む
	 */
	@Test
	void assertWith() {
		Address address1 = new Address("東京都", "千代田区千代田");
		Person person1 = new Person("内立", 20, address1);
		var personList = List.of(person1);

		Assertions.assertWith(personList.get(0).address(), v -> {
			Assertions.assertThat(v.prefecture()).isEqualTo("東京都");
			Assertions.assertThat(v.following()).isEqualTo("千代田区千代田");
		});
	}

	/**
	 * コレクションが変更不可かの検証
	 */
	@Test
	void isUnmodifiable() {
		Assertions.assertThat(Collections.unmodifiableCollection(new ArrayList<>()))
				.isUnmodifiable();
		Assertions.assertThat(Collections.unmodifiableList(new ArrayList<>()))
				.isUnmodifiable();
		Assertions.assertThat(Collections.unmodifiableSet(new HashSet<>()))
				.isUnmodifiable();
	}
}
