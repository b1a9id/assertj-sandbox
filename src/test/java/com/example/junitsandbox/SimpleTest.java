package com.example.junitsandbox;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SimpleTest {

	@Test
	public void propertiesAssertion() {
		Brand brand = new Brand("stof", "Tanita", Gender.MAN);
		Assert.assertEquals("Tanita", brand.getDesigner());
		Assert.assertEquals(Gender.MAN, brand.getGender());
	}

	@Test
	public void collectionAssertion() {
		Brand stof = new Brand("stof", "Tanita", Gender.MAN);
		Brand ethosens = new Brand("ethosens", "Hashimoto", Gender.MAN);
		List<Brand> brands = Arrays.asList(stof, ethosens);

		Brand brandZero = brands.get(0);
		Assert.assertEquals("Tanita", brandZero.getDesigner());
		Assert.assertEquals(Gender.MAN, brandZero.getGender());

		Brand brandFirst = brands.get(1);
		Assert.assertEquals("Hashimoto", brandFirst.getDesigner());
		Assert.assertEquals(Gender.MAN, brandFirst.getGender());
	}
}
