package com.practice.itx.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StockModelTest {

    @Test
    void testSetSizesWithValidValues() {
        StockModel stock = new StockModel();
        stock.setSizes(Map.of(Size.S, 10, Size.M, 5));
        assertThat(stock.getSizes()).containsEntry(Size.S, 10).containsEntry(Size.M, 5);
    }

    @Test
    void testSetSizesAsNullShouldBeEmpty() {
        StockModel stock = new StockModel();
        stock.setSizes(null);
        assertThat(stock.getSizes()).isEmpty();
    }

    @Test
    void testSetSizesAsEmptyMapShouldRemainEmpty() {
        StockModel stock = new StockModel();
        stock.setSizes(Collections.emptyMap());
        assertThat(stock.getSizes()).isEmpty();
    }

    @Test
    void testCalculateStockRatioWeight() {
        StockModel stock = new StockModel();
        stock.setSizes(Map.of(Size.S, 10, Size.M, 0, Size.L, 5));

        double weight = stock.calculateStockRatioWeight(2.0);
        assertThat(weight).isEqualTo((2.0 * 2) / 3);
    }
}