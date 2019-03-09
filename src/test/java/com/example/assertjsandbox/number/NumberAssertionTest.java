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
}
