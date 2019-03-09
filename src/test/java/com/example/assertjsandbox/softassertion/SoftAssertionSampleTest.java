package com.example.assertjsandbox.softassertion;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

class SoftAssertionSampleTest {

	/**
	 * SoftAssertions#assertAllを使う。
	 * 複数の検証がある場合にテストを止めることなく、全体を検証した後にログを出力する
	 */
	@Test
	void notMatchDesignerForAssertAll() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		// 直接的にassertThatを使わずにSoftAssertionsを使う
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(stof.getName()).as("Brand").isEqualTo("stof");
		softAssertions.assertThat(stof.getDesigner()).as("Desinger").isEqualTo("Tanita");
		softAssertions.assertThat(stof.getGender()).as("Gender").isEqualTo(Gender.MAN);
		// SoftAssertionsの全体の検証をするメソッドを呼ぶ
		softAssertions.assertAll();
	}

	/**
	 * SoftAssertions#assertSoftlyを使う。z
	 * 複数の検証がある場合にテストを止めることなく、全体を検証した後にログを出力する
	 */
	@Test
	void notMatchDesignerForAssertSoftlyMethod() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(stof.getName()).as("Brand").isEqualTo("stof");
			softAssertions.assertThat(stof.getDesigner()).as("Desinger").isEqualTo("Tanita");
			softAssertions.assertThat(stof.getGender()).as("Gender").isEqualTo(Gender.MAN);
			// assertSoftlyメソッドによりassertAllを実行されるので、呼ぶ必要はない。
		});
	}
}
