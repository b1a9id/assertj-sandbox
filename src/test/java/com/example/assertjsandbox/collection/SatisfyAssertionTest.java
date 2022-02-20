package com.example.assertjsandbox.collection;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

class SatisfyAssertionTest {

	@Test
	void satisfy() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		assertThat(brands)
				.satisfies(brand -> {
							assertThat(brand.name()).isEqualTo("bedsidedrama");
							assertThat(brand.designer()).isEqualTo("Tanita");
							assertThat(brand.gender()).isEqualTo(Gender.MAN);
						}, atIndex(1)
				).noneSatisfy(brand -> assertThat(brand.name()).isEqualTo("Portaille"));
	}

	@Test
	void hasOnlyOneElementSatisfying() {
		List<Brand> brands = List.of(new Brand("ETHOSENS", "Hashimoto", Gender.MAN));
		assertThat(brands)
				.singleElement()
				.extracting(Brand::name, Brand::designer, Brand::gender)
				.containsExactly("ETHOSENS", "Hashimoto", Gender.MAN);
	}
}
