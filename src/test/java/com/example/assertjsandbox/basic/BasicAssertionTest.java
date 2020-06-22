package com.example.assertjsandbox.basic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BasicAssertionTest {

	/**
	 * 空白を含んでいるかの検証
	 */
	@Test
	void containsWhitespacesToStringAndSequence() {
		assertThat(" ").containsWhitespaces();
		assertThat("a b").containsWhitespaces();
		assertThat(" c ").containsWhitespaces();
		assertThat("　").containsWhitespaces();
	}

	/**
	 * 空白を含んでいないかの検証
	 */
	@Test
	void doesNotContainWhitespacesToStringAndSequence() {
		assertThat("a").doesNotContainAnyWhitespaces();
		assertThat("").doesNotContainAnyWhitespaces();
		assertThat("ab").doesNotContainAnyWhitespaces();
		String nullVal = null;
		assertThat(nullVal).doesNotContainAnyWhitespaces();
	}

	/**
	 * InputStreamが指定した値を持っているかの検証
	 */
	@Test
	void hasContentStringArg() {
		InputStream inputStream = new ByteArrayInputStream("abc".getBytes());
		assertThat(inputStream)
				.hasContent("abc");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値より小さいことの検証
	 */
	@Test
	void isLessThan() {
		assertThat("abc")
				.isLessThan("bcd")
				.isLessThan("b")
				.isLessThan("abca");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値以下であることの検証
	 */
	@Test
	void isLessThanOrEqualTo() {
		assertThat("abc")
				.isLessThanOrEqualTo("bcd")
				.isLessThanOrEqualTo("abc")
				.isLessThanOrEqualTo("b")
				.isLessThanOrEqualTo("abca");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値より大きいことの検証
	 */
	@Test
	void isGreaterThan() {
		assertThat("xyz")
				.isGreaterThan("bcd")
				.isGreaterThan("xy")
				.isGreaterThan("ABC");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値以上であるの検証
	 */
	@Test
	void isGreaterThanOrEqualTo() {
		assertThat("xyz")
				.isGreaterThanOrEqualTo("abc")
				.isGreaterThanOrEqualTo("xyz")
				.isGreaterThanOrEqualTo("xy")
				.isGreaterThanOrEqualTo("ABC");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値の間にあること（両端含む）
	 */
	@Test
	void isBetween() {
		assertThat("ab")
				.isBetween("aa", "ac")
				.isBetween("ab", "ac")
				.isBetween("aa", "ab")
				.isBetween("ab", "ab")
				.isBetween("a", "c");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値の間にあること（両端含まない）
	 */
	@Test
	void isStrictlyBetween() {
		assertThat("ab")
				.isStrictlyBetween("aa", "ac")
				.isStrictlyBetween("a", "c");
	}

	/**
	 * Conditionクラスで宣言した検証条件を満たしていることの検証
	 */
	@Test
	void satisfies() {
		Condition<String> brandCondition = new Condition<>(s -> s.startsWith("b"), "brand start");
		assertThat("bedsidedrama")
				.satisfies(brandCondition);
	}

	/**
	 * 正しくBase64エンコードされた文字列かどうかの検証
	 */
	@Test
	void assertIsBase64() {
		Assertions.assertThat("QXNzZXJ0Sg==").isBase64();
		// パディングなし
		Assertions.assertThat("QXNzZXJ0Sg").isBase64();
	}

	/**
	 * Base64でデコードした文字列の検証
	 */
	@Test
	void assertDecodedAsBase64() {
		Assertions.assertThat("QXNzZXJ0Sg==")
				.decodedAsBase64()
				.containsExactly("AssertJ".getBytes());
		// パディングなし
		Assertions.assertThat("QXNzZXJ0Sg")
				.decodedAsBase64()
				.containsExactly("AssertJ".getBytes());
	}
}
