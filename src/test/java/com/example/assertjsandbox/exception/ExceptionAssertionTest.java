package com.example.assertjsandbox.exception;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;

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

	/**
	 * ExceptionのCause and/or Root Causeの検証をする
	 */
	@Test
	void checkingCause() {
		NullPointerException rootCause = new NullPointerException("root cause message");
		IllegalArgumentException cause = new IllegalArgumentException("cause message", rootCause);
		Throwable throwable = new Throwable("top level", cause);

		Assertions.assertThat(throwable)
				.hasMessage("top level")
				.getRootCause()
				.hasMessage("root cause message");
		Assertions.assertThat(throwable)
				.hasMessage("top level")
				.getCause()
				.hasMessage("cause message");
	}

	/**
	 * ExceptionのCause and/or Root Causeの検証をする（assertThatExceptionOfType）
	 */
	@Test
	void checkingCauseWithAssertThatExceptionOfType() {
		NullPointerException rootCause = new NullPointerException("root cause message");
		IllegalArgumentException cause = new IllegalArgumentException("cause message", rootCause);
		RuntimeException runtimeException = new RuntimeException(cause);

		Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> { throw cause; })
				.havingCause()
				.withMessage("root cause message");

		Assertions.assertThatExceptionOfType(RuntimeException.class)
				.isThrownBy(() -> { throw runtimeException; })
				.havingRootCause()
				.withMessage("root cause message");
	}

}
