package com.example.assertjsandbox.object;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Address;
import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import com.example.assertjsandbox.model.InitialCharBrand;
import com.example.assertjsandbox.model.Person;

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
				.usingRecursiveComparison()
				.isEqualTo(cloneStof);
	}

	/**
	 * 指定したフィールドの値を検証
	 */
	@Test
	void addExtractingFunctionArg() {
		Brand brand = new Brand("STOF", "Tanita", Gender.MAN);
		assertThat(brand)
				.extracting(Brand::name)
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
				.usingRecursiveComparison()
				.ignoringFields("name", "designer")
				.isEqualTo(ethosens)
				.ignoringFields("name")
				.isEqualTo(storama);
	}

	/**
	 * nullのフィールドは無視して検証
	 */
	@Test
	void ignoreNullFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand storama = new Brand(null, "Tanita", Gender.MAN);

		// isEqualToIgnoringNullFieldsのstoramaにあるnullであるnameフィールドは無視される
		Assertions.assertThat(stof)
				.usingRecursiveComparison()
				.ignoringExpectedNullFields()
				.isEqualTo(storama);
	}

	/**
	 * ネストしたオブジェクトの検証
	 */
	@Test
	void nestObjectFieldAssertions() {
		Address address1 = new Address("東京都", "千代田区千代田");
		Person person1 = new Person("内立", 20, address1);

		Address address2 = new Address("東京都", "渋谷区東");
		Person person2 = new Person("良介", 20, address2);

		Address address3 = new Address("東京都", null);
		Person person3 = new Person(null, 20, address3);

		// 指定したフィールドを無視する
		Assertions.assertThat(person1).usingRecursiveComparison()
				.ignoringFields("name", "address.following")
				.isEqualTo(person2);
		Assertions.assertThat(person1).usingRecursiveComparison()
				.ignoringFields("name", "address")
				.isEqualTo(person2);

		// 実測値でnullのフィールドを無視する
		Assertions.assertThat(person3).usingRecursiveComparison()
				.ignoringActualNullFields()
				.isEqualTo(person1);
		// 期待値でnullのフィールドを無視する
		Assertions.assertThat(person1).usingRecursiveComparison()
				.ignoringExpectedNullFields()
				.isEqualTo(person3);
	}

	/**
	 * オブジェクトが指定したフィールドのみ持つことの検証（過不足はfailed）
	 */
	@Test
	void hasOnlyFields() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);

		Assertions.assertThat(stof).hasOnlyFields("name", "designer", "gender");
	}
}
