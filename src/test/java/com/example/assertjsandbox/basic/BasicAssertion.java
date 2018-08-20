package com.example.assertjsandbox.basic;

import com.sun.javafx.sg.prism.web.NGWebView;
import org.assertj.core.api.Condition;
import org.junit.Test;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Struct;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAssertion {

	/**
	 * 空白を含んでいるかの検証
	 */
	@Test
	public void containsWhitespacesToStringAndSequence() {
		assertThat(" ").containsWhitespaces();
		assertThat("a b").containsWhitespaces();
		assertThat(" c ").containsWhitespaces();
		assertThat("　").containsWhitespaces();
	}

	/**
	 * 空白を含んでいないかの検証
	 */
	@Test
	public void doesNotContainWhitespacesToStringAndSequence() {
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
	public void hasContentStringArg() {
		InputStream inputStream = new ByteArrayInputStream("abc".getBytes());
		assertThat(inputStream)
				.hasContent("abc");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値より小さいことの検証
	 */
	@Test
	public void isLessThan() {
		assertThat("abc")
				.isLessThan("bcd")
				.isLessThan("b")
				.isLessThan("abca");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値以下であることの検証
	 */
	@Test
	public void isLessThanOrEqualTo() {
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
	public void isGreaterThan() {
		assertThat("xyz")
				.isGreaterThan("bcd")
				.isGreaterThan("xy")
				.isGreaterThan("ABC");
	}

	/**
	 * {@link String#compareTo(String)} の結果が与えられた値以上であるの検証
	 */
	@Test
	public void isGreaterThanOrEqualTo() {
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
	public void isBetween() {
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
	public void isStrictlyBetween() {
		assertThat("ab")
				.isStrictlyBetween("aa", "ac")
				.isStrictlyBetween("a", "c");
	}

	/**
	 * Conditionクラスで宣言した検証条件を満たしていることの検証
	 */
	@Test
	public void satisfies() {
		Condition<String> brandCondition = new Condition<>(s -> s.startsWith("b"), "brand start");
		assertThat("bedsidedrama")
				.satisfies(brandCondition);
	}
}
