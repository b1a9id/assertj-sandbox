package com.example.assertjsandbox.collection;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

public class SatisfyAssertion {

	@Test
	public void satisfy() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		assertThat(brands)
				.satisfies(brand -> {
							assertThat(brand.getName()).isEqualTo("bedsidedrama");
							assertThat(brand.getDesigner()).isEqualTo("Tanita");
							assertThat(brand.getGender()).isEqualTo(Gender.MAN);
						}, atIndex(1)
				).noneSatisfy(brand -> assertThat(brand.getName()).isEqualTo("Portaille"));
	}
}
