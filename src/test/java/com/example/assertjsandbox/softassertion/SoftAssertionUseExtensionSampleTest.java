package com.example.assertjsandbox.softassertion;

import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

@ExtendWith(SoftAssertionsExtension.class)
class SoftAssertionUseExtensionSampleTest {

	/**
	 * 複数の検証がある場合にテストを止めることなく、全体を検証した後にログを出力する
	 */
	@Test
	void notMatchDesignerForAssertAll(BrandSoftAssertions softAssertions) {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		softAssertions.assertThat(stof)
				.hasName("stof")
				.hasGender(Gender.MAN);
	}
}
