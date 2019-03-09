package com.example.assertjsandbox.exception;

import com.example.assertjsandbox.model.Brand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ExceptionAssertionTest {
	/**
	 * throwされたExceptionクラスとメッセージの検証
	 */
	@Test
	void assertionExceptionClassAndMessage() {
		Assertions.assertThatThrownBy(() -> {
			throw new Exception("exception!!!");
		}).isInstanceOf(Exception.class).hasMessage("exception!!!");
	}

	/**
	 * NullPointerExceptionがthrowされることの検証
	 */
	@Test
	void assertionIllegalArgumentException() {
		String test = null;
		Assertions.assertThatNullPointerException()
				.isThrownBy(() -> test.toUpperCase());
	}

	/**
	 * throwされたExceptionクラスとメッセージの検証
	 */
	@Test
	void assertionExceptionClassAndMessageAndCause() {
		Assertions.assertThatExceptionOfType(IOException.class)
				.isThrownBy(() -> {
					throw new IOException("exception!!!");
				}).withMessage("%s!!!", "exception")
				.withMessageContaining("exception")
				.withNoCause();
	}

	/**
	 * ExceptionがThrowされないことの検証
	 */
	@Test
	void noThrowException() {
		Assertions.assertThatCode(Brand::new).doesNotThrowAnyException();
	}

	/**
	 * Throwable変数を用意した場合の検証
	 */
	@Test
	void throwableVariableAssertion() {
		Throwable thrown = Assertions.catchThrowable(() -> {
			throw new Exception("exception!!!");
		});

		Assertions.assertThat(thrown)
				.isInstanceOf(Exception.class)
				.hasMessageContaining("exception!!!");
	}
}
