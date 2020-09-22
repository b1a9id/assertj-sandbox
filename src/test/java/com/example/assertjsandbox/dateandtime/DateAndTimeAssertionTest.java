package com.example.assertjsandbox.dateandtime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Period;

class DateAndTimeAssertionTest {

	/**
	 * Periodクラスの検証
	 */
	@Test
	void hasDays() {
		Assertions.assertThat(Period.of(5, 10, 2))
				.hasYears(5)
				.hasMonths(10)
				.hasDays(2)
				.isPositive();

		Assertions.assertThat(Period.ofYears(-5))
				.hasYears(-5)
				.hasMonths(0)
				.hasDays(0)
				.isNegative();
	}
}
