package com.example.assertjsandbox.softassertion;

import org.assertj.core.api.AbstractSoftAssertions;
import org.assertj.core.api.StandardSoftAssertionsProvider;

import com.example.assertjsandbox.custom.BrandAssert;
import com.example.assertjsandbox.model.Brand;

public class BrandSoftAssertions extends AbstractSoftAssertions implements StandardSoftAssertionsProvider {

    public BrandAssert assertThat(Brand actual) {
        return proxy(BrandAssert.class, Brand.class, actual);
    }
}
