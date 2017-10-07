package com.example.assertjsandbox.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.*;
import java.util.*;

public class AssertionList {

	@Test
	public void asList() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand sneeuw = new Brand("sneeuw", "Yukiura", Gender.WOMAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		Brand dulcamara = new Brand("Dulcamara", "Yoda", Gender.WOMAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, sneeuw, ethosens, dulcamara);

		Assertions.assertThat(brands).asList();
	}

	@Test
	public void listAssertion () {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ETHOSENS", "Hashimoto", Gender.MAN);
		List<Brand> brands = Arrays.asList(stof, bedsidedrama, ethosens);

		Assertions.assertThat(brands)
				.extracting(Brand::getName)
				.containsExactly("stof", "bedsidedrama", "ETHOSENS");
	}

	@Test
	public void instanceOf() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);

		Assertions.assertThat(stof).isInstanceOf(Brand.class);
		Assertions.assertThat(stof).isNotInstanceOf(InitialCharBrand.class);
	}

	@Test
	public void nullAssertion() {
		String nullStr = null;
		String notNullStr = "TEST";
		String blackStr = "";

		Assertions.assertThat(nullStr).isNull();
		Assertions.assertThat(notNullStr).isNotNull();
		Assertions.assertThat(blackStr).isNullOrEmpty();
		Assertions.assertThat(nullStr).isNullOrEmpty();
	}

	/**
	 * 同じインスタンスであるかの検証
	 */
	@Test
	public void sameInstance() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Assertions.assertThat(stof).isSameAs(stof);
	}

	@Test
	public void localDateAssert() {
		LocalDate now = LocalDate.now();
		LocalDate past = LocalDate.of(2017, 1,1);
		LocalDate future = LocalDate.of(2099, 12,31);

		Assertions.assertThat(now).isAfter(past);
		Assertions.assertThat(past).isBefore(now);
		Assertions.assertThat(now).isBetween(past, future);
		Assertions.assertThat(now).isToday();
	}
}
