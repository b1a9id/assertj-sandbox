package com.example.assertjsandbox.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FieldByFieldComparisons {

	/**
	 * 全フィールドの値を検証
	 */
	@Test
	public void allFieldAssertion() {
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
	public void specificFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand storama = new Brand("storama", "Tanita", Gender.MAN);

		Assertions.assertThat(stof)
				.isEqualToComparingOnlyGivenFields(ethosens, "gender")
				.isEqualToComparingOnlyGivenFields(storama, "designer", "gender");
	}

	/**
	 * 指定したフィールド以外の値を検証
	 */
	@Test
	public void ignoreSpecificFieldAssertion() {
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
	public void ignoreNullFieldAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand storama = new Brand(null, "Tanita", Gender.MAN);

		// isEqualToIgnoringNullFieldsのstoramaにあるnullであるnameフィールドは無視される
		Assertions.assertThat(stof).isEqualToIgnoringNullFields(storama);
	}
}
