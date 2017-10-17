package com.example.assertjsandbox.optional;

import com.example.assertjsandbox.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

public class OptionalAssertion {

	/**
	 * 値がemptyかどうかの検証
	 */
	@Test
	public void present() {
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
	public void contains() {
		// AbstractOptionalAssert#containsはAbstractOptionalAssert#hasValueのalias
		Assertions.assertThat(Optional.of("stof"))
				.hasValue("stof")
				.contains("stof");
	}

	/**
	 * 値が指定した条件を満たしているかどうかの検証
	 */
	@Test
	public void valueSatisfying() {
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
	public void containsInstanceOf() {
		Assertions.assertThat(Optional.of("stof"))
				.containsInstanceOf(String.class);
	}

	/**
	 * 値が同じインスタンスでことの検証
	 */
	@Test
	public void containsSame() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand stofCopy = stof;

		Assertions.assertThat(Optional.of(stof))
				.containsSame(stofCopy);
	}

	@Test
	public void map() {
		Assertions.assertThat(Optional.of("stof"))
				.contains("stof")
				.map(String::length)
				.contains(4);
	}

	@Test
	public void flatMap() {
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
