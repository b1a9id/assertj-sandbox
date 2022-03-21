package com.example.assertjsandbox.number;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberAssertionTest {

	/**
	 * 指定した範囲内の値かの検証
	 */
	@Test
	void isBetween() {
		Assertions.assertThat(100)
				.isBetween(0, 1000);
	}

	/**
	 * 値の検証
	 */
	@Test
	void valueAssertion() {
		Assertions.assertThat(-1)
				.isNotZero()
				.isNotPositive()
				.isNegative();

		Assertions.assertThat(1)
				.isNotZero()
				.isPositive()
				.isOne()
				.isNotNegative();
	}

	/**
	 * 奇数か偶数かの検証
	 */
	@Test
	void evenOrOdd() {
		Assertions.assertThat(12).isEven();
		Assertions.assertThat(-46).isEven();

		Assertions.assertThat(3).isOdd();
		Assertions.assertThat(-17).isOdd();
	}

	/**
	 * float と doubleの値が有限でないことの検証
	 */
	@Test
	void isNotFinite() {
		Assertions.assertThat(Double.POSITIVE_INFINITY).isNotFinite();
		Assertions.assertThat(Double.NEGATIVE_INFINITY).isNotFinite();
		Assertions.assertThat(Double.NaN).isNotFinite();

		Assertions.assertThat(Float.POSITIVE_INFINITY).isNotFinite();
		Assertions.assertThat(Float.NEGATIVE_INFINITY).isNotFinite();
		Assertions.assertThat(Float.NaN).isNotFinite();
	}

	/**
	 * float と doubleの値が正の無限大でも負の無限大でもないことを検証
	 */
	@Test
	void isNotInfinite() {
		Assertions.assertThat(1.0).isNotInfinite();
		Assertions.assertThat(Double.NaN).isNotInfinite();
	}
}
