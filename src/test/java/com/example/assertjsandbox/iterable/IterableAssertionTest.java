package com.example.assertjsandbox.iterable;

import com.example.assertjsandbox.model.Brand;
import com.example.assertjsandbox.model.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.parseDatetimeWithMs;

class IterableAssertionTest {

    /**
     * Iterable/Arrayの要素順、要素数、要素が正しいかの検証
     */
    @Test
    void satisfiesExactly() {
        Brand stof = new Brand("stof", "Tanita", Gender.MAN);
        Brand bedsidedrama = new Brand("bedsidedrama", "Tanita", Gender.MAN);

        Iterable<Brand> brandIterable = List.of(stof, bedsidedrama);
        Assertions.assertThat(brandIterable)
                .satisfiesExactly(
                        brand -> Assertions.assertThat(brand)
                                .extracting(Brand::getName, Brand::getDesigner, Brand::getGender)
                                .containsExactly("stof", "Tanita", Gender.MAN),
                        brand -> Assertions.assertThat(brand)
                                .extracting(Brand::getName, Brand::getDesigner, Brand::getGender)
                                .containsExactly("bedsidedrama", "Tanita", Gender.MAN)
                );

        Brand[] brandArray = { stof, bedsidedrama };
        Assertions.assertThat(brandArray)
                .satisfiesExactly(
                        brand -> Assertions.assertThat(brand)
                                .extracting(Brand::getName, Brand::getDesigner, Brand::getGender)
                                .containsExactly("stof", "Tanita", Gender.MAN),
                        brand -> Assertions.assertThat(brand)
                                .extracting(Brand::getName, Brand::getDesigner, Brand::getGender)
                                .containsExactly("bedsidedrama", "Tanita", Gender.MAN)
                );
    }

    /**
     * Iterable/Arrayの要素数、要素が正しいかの検証
     */
    @Test
    void satisfiesExactlyInAnyOrder() {
        List<String> brandNames = List.of("stof", "bedsidedrama", "ethosens");
        Assertions.assertThat(brandNames)
                .satisfiesExactlyInAnyOrder(
                        brandName -> Assertions.assertThat(brandName).contains("s"),
                        brandName -> Assertions.assertThat(brandName).startsWith("b"),
                        brandName -> Assertions.assertThat(brandName).hasSize(8).doesNotContain("a"));
    }

    @Test
    void instantToDateAssertions() {
        final Date dateTimeWithMs = parseDatetimeWithMs("2001-02-03T04:05:06.700");

        Assertions.assertThat(dateTimeWithMs).isEqualTo(dateTimeWithMs.toInstant())
                .isBefore(Instant.parse("2002-01-01T00:00:00.00Z"))
                .isAfter(Instant.parse("2000-01-01T00:00:00.00Z"))
                .isBetween(Instant.parse("2000-01-01T00:00:00.00Z"),
                        Instant.parse("2002-01-01T00:00:00.00Z"))
                .isCloseTo(dateTimeWithMs.toInstant().minusMillis(10), 20)
                .isEqualToIgnoringHours(dateTimeWithMs.toInstant().plus(1, ChronoUnit.HOURS))
                .isEqualToIgnoringMinutes(dateTimeWithMs.toInstant().plus(1, ChronoUnit.MINUTES))
                .isEqualToIgnoringSeconds(dateTimeWithMs.toInstant().plus(1, ChronoUnit.SECONDS))
                .isEqualToIgnoringMillis(dateTimeWithMs.toInstant().plus(1, ChronoUnit.MILLIS))
                .isIn(dateTimeWithMs.toInstant(), dateTimeWithMs.toInstant().plusMillis(10))
                .isInSameDayAs(dateTimeWithMs.toInstant().plus(1, ChronoUnit.MINUTES))
                .isInSameMonthAs(Instant.parse("2001-02-01T00:00:00.00Z"))
                .isInSameYearAs(Instant.parse("2001-01-01T00:00:00.00Z"))
                .isNotIn(dateTimeWithMs.toInstant().minusMillis(10), dateTimeWithMs.toInstant().plusMillis(10));
    }
}
