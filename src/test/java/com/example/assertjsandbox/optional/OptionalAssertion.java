package com.example.assertjsandbox.optional;

import java.util.Optional;
import java.util.function.Function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

class OptionalAssertion {

	/**
	 * 値がemptyかどうかの検証
	 */
	@Test
	void present() {
		// AbstractOptionalAssert#isNotEmptyはAbstractOptionalAssert#isPresentのalias
		Assertions.assertThat(Optional.of("stof"))
				.isNotEmpty()
				.isPresent();

		// AbstractOptionalAssert#isEmptyはAbstractOptionalAssert#isNotPresentのalias
		Assertions.assertThat(Optional.empty())
				.isEmpty()
				.isNotPresent();
	}

	/**
	 * 指定した値を含んでいるかどうかの検証
	 */
	@Test
	void contains() {
		// AbstractOptionalAssert#containsはAbstractOptionalAssert#hasValueのalias
		Assertions.assertThat(Optional.of("stof"))
				.hasValue("stof")
				.contains("stof");
	}

	/**
	 * 値が指定した条件を満たしているかどうかの検証
	 */
	@Test
	void valueSatisfying() {
		Assertions.assertThat(Optional.of(10))
				.hasValueSatisfying(value -> {
					Assertions.assertThat(value).isGreaterThan(9);
					Assertions.assertThat(value).isLessThan(11);
				});
	}

	/**
	 * 値が指定したクラスのものかどうかの検証
	 */
	@Test
	void containsInstanceOf() {
		Assertions.assertThat(Optional.of("stof"))
				.containsInstanceOf(String.class);
	}

	/**
	 * 値が同じインスタンスでことの検証
	 */
	@Test
	void containsSame() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand stofCopy = stof;

		Assertions.assertThat(Optional.of(stof))
				.containsSame(stofCopy);
	}

	/**
	 * オブジェクトの検証
	 */
	@Test
	void objectAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Assertions.assertThat(Optional.of(stof))
				.hasValueSatisfying(b -> {
					Assertions.assertThat(b.getName()).isEqualTo("stof");
					Assertions.assertThat(b.getDesigner()).isEqualTo("Tanita");
					Assertions.assertThat(b.getGender()).isEqualTo(Gender.MAN);
				});
	}

	@Test
	void map() {
		Assertions.assertThat(Optional.of("stof"))
				.contains("stof")
				.map(String::length)
				.contains(4);
	}

	@Test
	void flatMap() {
		Function<String, Optional<String>> UPPER_CASE_OPTIONAL_STRING =
				s -> s == null ? Optional.empty() : Optional.of(s.toUpperCase());

		Assertions.assertThat(Optional.of("stof"))
				.flatMap(UPPER_CASE_OPTIONAL_STRING)
				.contains("STOF");
		Assertions.assertThat(Optional.<String>empty())
				.flatMap(UPPER_CASE_OPTIONAL_STRING)
				.isEmpty();
	}

}
