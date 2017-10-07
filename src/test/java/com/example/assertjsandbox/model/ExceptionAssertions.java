package com.example.assertjsandbox.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;

public class ExceptionAssertions {
	/**
	 * throwされたExceptionクラスとメッセージの検証
	 */
	@Test
	public void assertionExceptionClassAndMessage() {
		Assertions.assertThatThrownBy(() -> {
			throw new Exception("exception!!!");
		}).isInstanceOf(Exception.class).hasMessage("exception!!!");
	}

	/**
	 * throwされたExceptionクラスとメッセージの検証
	 */
	@Test
	public void assertionExceptionClassAndMessegeAndCause() {
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
	public void noThrowException() {
		Assertions.assertThatCode(Brand::new).doesNotThrowAnyException();
	}

	/**
	 * Throwable変数を用意した場合の検証
	 */
	@Test
	public void throwableVariableAssertion() {
		Throwable thrown = Assertions.catchThrowable(() -> {
			throw new Exception("exception!!!");
		});

		Assertions.assertThat(thrown)
				.isInstanceOf(Exception.class)
				.hasMessageContaining("exception!!!");
	}
}