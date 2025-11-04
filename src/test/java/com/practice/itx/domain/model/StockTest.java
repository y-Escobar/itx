package com.practice.itx.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StockTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("setSizes(null|empty) => getSizes() empty, never null")
    void testSetSizesAsNullOrEmptyReturnsEmpty(Map<Size,Integer> input) {
        Stock stock = new Stock();
        stock.setSizes(input);
        assertThat(stock.getSizes()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Defensive copy: mutating the new Stock again won't affect the already set")
    void testSetSizesWithDefensiveCopy() {
        Map<Size, Integer> newStock = new HashMap<>(){{
            put(Size.S, 10);
        }};

        Stock stock = new Stock();
        stock.setSizes(newStock);

        newStock.put(Size.M, 99);

        assertThat(stock.getSizes())
                .containsEntry(Size.S, 10)
                .doesNotContainEntry(Size.M, 99);
    }

    @Test
    @DisplayName("getSizes() cannot be modified")
    void testUnmodifiableGetSizes() {
        var stock = new Stock();
        stock.setSizes(Map.of(Size.S, 1));
        assertThatThrownBy(() -> stock.getSizes().put(Size.M, 2))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("AllArgsConstructor with null Sizes → getSizes() empty and immutable")
    void testAllArgsConstructorWithNullSizesIsSafe() {
        var stock = new Stock("id1", null);
        assertThat(stock.getSizes()).isNotNull().isEmpty();
        assertThatThrownBy(() -> stock.getSizes().put(Size.S, 1)).isInstanceOf(UnsupportedOperationException.class);
    }
}