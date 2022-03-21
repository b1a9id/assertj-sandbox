package com.example.assertjsandbox.basic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.condition.MappedCondition;
import org.assertj.core.condition.VerboseCondition;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

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

	/**
	 * ドメインとリクエストパラメータのkey-valueが正しいかの検証（順不同）
	 */
	@Test
	void assertIsEqualToWithSortedQueryParameters() throws Exception {
		URL blog = new URL("https://www.b1a9idps.com?p1=uchitate&p2=ryosuke");

		Assertions.assertThat(blog)
				.isEqualToWithSortedQueryParameters(new URL("https://www.b1a9idps.com?p1=uchitate&p2=ryosuke"))
				.isEqualToWithSortedQueryParameters(new URL("https://www.b1a9idps.com?p2=ryosuke&p1=uchitate"));
	}

	/**
	 * InputStreamが持っているバイナリが正しいかの検証
	 */
	@Test
	void assertHasBinaryContent() {
		InputStream inputStream = new ByteArrayInputStream(new byte[] {1, 2});

		Assertions.assertThat(inputStream).hasBinaryContent(new byte[] {1, 2});
	}

	/**
	 * List中に1つしか含まれない要素かどうかの検証
	 */
	@Test
	void assertContainsOnlyOnceElementsOf() {
		Assertions.assertThat(List.of("stof", "bedsidedrama", "ETHOSENS"))
				.containsOnlyOnceElementsOf(List.of("stof"))
				.containsOnlyOnceElementsOf(List.of("bedsidedrama", "ETHOSENS"));
	}

	/**
	 * InputStreamが空かどうかの検証
	 */
	@Test
	void inputStreamEmptyOrNotEmpty() {
		Assertions.assertThat(new ByteArrayInputStream(new byte[] {})).isEmpty();
		Assertions.assertThat(new ByteArrayInputStream(new byte[] {0xa})).isNotEmpty();
	}

	/**
	 * 指定した値を含んでいるかどうかの検証
	 */
	@Test
	void doesNotContainIgnoringCase() {
		Assertions.assertThat("stof")
				.containsIgnoringCase("st")
				.doesNotContainIgnoringCase("ETHOSENS", "bedside");
	}

	@Test
	void isEqualToNormalizingUnicode() {
		// Ä = \u00C4 - Ä = \u0041\u0308
		assertThat("Ä").isEqualToNormalizingUnicode("Ä");
		assertThat("\u00C4").isEqualToNormalizingUnicode("\u0041\u0308");
	}

	/**
	 * ハッシュコードが異なることの検証
	 */
	@Test
	void doesNotHaveSameHashCodeAs() {
		Assertions.assertThat(42L).doesNotHaveSameHashCodeAs(250L);
		Assertions.assertThat("stof").doesNotHaveSameHashCodeAs("bedsidedrama");
	}

	/**
	 * Object#toString()の検証
	 */
	@Test
	void doesNotHaveToString() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);

		// これまで
		Assertions.assertThat(stof.toString()).isNotEqualTo("TEST");
		// これから
		Assertions.assertThat(stof).doesNotHaveToString("TEST");
		Assertions.assertThat(stof).hasToString(stof.toString());
	}

	/**
	 * 指定したパスにあるファイルが空かどうかの検証
	 */
	@Test
	void isEmptyFile() {
		Path noEmpty = Paths.get("src/test/resources/txt/has-content.txt");
		Assertions.assertThat(noEmpty).isNotEmptyFile();

		Path empty = Paths.get("src/test/resources/txt/no-content.txt");
		Assertions.assertThat(empty).isEmptyFile();
	}

	/**
	 * 空白を無視して文字列を含んでいるかの検証
	 */
	@Test
	void containsIgnoringWhitespaces() {
		Assertions.assertThat("My favorite brand is ETHOSENS")
				.containsIgnoringWhitespaces("rand")
				.containsIgnoringWhitespaces("favorite", "ETHOSENS")
				.containsIgnoringWhitespaces("favoritebrand")
				.containsIgnoringWhitespaces("isETHOSEN S");
	}

	/**
	 * AbstractInputStreamAssertでStringとしての検証
	 */
	@Test
	void inputStreamToString() {
		var inputStream = new ByteArrayInputStream("abc".getBytes());

		Assertions.assertThat(inputStream)
				.asString(StandardCharsets.UTF_8)
				.startsWith("a")
				.endsWith("c");
	}

	/**
	 * マッピングの処理結果をConditionで検証
	 */
	@Test
	void mappedCondition() {
		Condition<String> startWithE = new Condition<>(s -> s.startsWith("E"), "start with 'E'");
		Condition<Optional<String>> optionalWithStartWithE =
				MappedCondition.mappedCondition(Optional::get, startWithE, "optional value start with 'E'");

		Assertions.assertThat(Optional.of("ETHOSENS"))
				.is(optionalWithStartWithE);
	}

	/**
	 * Conditionでの検証失敗時に失敗理由を詳しく表示
	 */
	@Test
	void verboseCondition() {
		Condition<String> shorterThan4 = VerboseCondition.verboseCondition(actual -> actual.length() < 4,
				"shorter than 4",
				s -> String.format(" but length was %s", s.length()));

		Assertions.assertThat("ETHOSENS")
				.is(shorterThan4);
	}

}
