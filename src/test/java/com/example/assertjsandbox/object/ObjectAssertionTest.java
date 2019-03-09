package com.example.assertjsandbox.object;

import com.example.assertjsandbox.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectAssertionTest {

	/**
	 * timeパッケージの検証
	 */
	@Test
	void localDateAssert() {
		LocalDate now = LocalDate.now();
		LocalDate past = LocalDate.of(2017, 1,1);
		LocalDate future = LocalDate.of(2099, 12,31);

		Assertions.assertThat(now).isAfter(past);
		Assertions.assertThat(past).isBefore(now);
		Assertions.assertThat(now).isBetween(past, future);
		Assertions.assertThat(now).isToday();
	}

	@Test
	void nullAssertion() {
		String nullStr = null;
		String notNullStr = "TEST";
		String blackStr = "";

		Assertions.assertThat(nullStr).isNull();
		Assertions.assertThat(notNullStr).isNotNull();
		Assertions.assertThat(blackStr).isNullOrEmpty();
		Assertions.assertThat(nullStr).isNullOrEmpty();
	}

	/**
	 * インスタンスのクラスを検証
	 */
	@Test
	void instanceOf() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);

		Assertions.assertThat(stof).isInstanceOf(Brand.class);
		Assertions.assertThat(stof).isNotInstanceOf(InitialCharBrand.class);
	}

	/**
	 * 同じインスタンスであるかの検証
	 */
	@Test
	void sameInstance() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Assertions.assertThat(stof).isSameAs(stof);
	}

	/**
	 * 全フィールドの値を検証
	 */
	@Test
	void allFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand cloneStof = new Brand("stof", "Tanita", Gender.MAN);

		// オブジェクトを比較すると異なる
		Assertions.assertThat(stof)
				.isNotEqualTo(cloneStof)
				.isEqualToComparingFieldByField(cloneStof);
	}

	/**
	 * 指定したフィールドの値を検証
	 */
	@Test
	void specificFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand storama = new Brand("storama", "Tanita", Gender.MAN);

		Assertions.assertThat(stof)
				.isEqualToComparingOnlyGivenFields(ethosens, "gender")
				.isEqualToComparingOnlyGivenFields(storama, "designer", "gender");
	}

	/**
	 * 指定したフィールドの値を検証
	 */
	@Test
	void addExtractingFunctionArg() {
		Brand brand = new Brand("STOF", "Tanita", Gender.MAN);
		assertThat(brand)
				.extracting(Brand::getName)
				.isEqualTo("STOF");
	}

	/**
	 * 指定したフィールド以外の値を検証
	 */
	@Test
	void ignoreSpecificFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand storama = new Brand("storama", "Tanita", Gender.MAN);

		Assertions.assertThat(stof)
				.isEqualToIgnoringGivenFields(ethosens, "name", "designer")
				.isEqualToIgnoringGivenFields(storama, "name");
	}

	/**
	 * nullのフィールドは無視して検証
	 */
	@Test
	void ignoreNullFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand storama = new Brand(null, "Tanita", Gender.MAN);

		// isEqualToIgnoringNullFieldsのstoramaにあるnullであるnameフィールドは無視される
		Assertions.assertThat(stof).isEqualToIgnoringNullFields(storama);
	}

	/**
	 * ネストしたオブジェクトの検証
	 */
	@Test
	void nestObjectFieldAssertions() {
		Address address1 = new Address("東京都", "千代田区千代田");
		Person person1 = new Person("内立", address1);

		Address address2 = new Address("東京都", "渋谷区東");
		Person person2 = new Person("良介", address2);
	}
}
