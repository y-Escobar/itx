package com.practice.itx.application.handler;

import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.model.Size;
import com.practice.itx.domain.model.StockModel;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSortingCriteriaHandlerTest {

    private final ProductSortingCriteriaHandler handler = new ProductSortingCriteriaHandler();

    @Test
    void testCalculateSortingWeightWithValidWeights() {
        StockModel stock = new StockModel("1", Map.of(Size.S, 10, Size.M, 5, Size.L, 10));
        ProductModel product = new ProductModel("1", "Test Product", 100, stock);
        SortingCriteriaDTO sortingCriteriaDTO = new SortingCriteriaDTO(1.5, 2.0);

        double weight = handler.calculateSortingWeight(product, sortingCriteriaDTO);

        assertThat(weight).isGreaterThan(0);
    }

    @Test
    void testCalculateSortingWeightWithNullWeights() {
        StockModel stock = new StockModel("1", Map.of(Size.S, 10, Size.M, 5, Size.L, 10));
        ProductModel product = new ProductModel("2", "Null Weights Product", 50, stock);
        SortingCriteriaDTO sortingCriteriaDTO = new SortingCriteriaDTO(null, null); // Pesos nulos

        double weight = handler.calculateSortingWeight(product, sortingCriteriaDTO);

        assertThat(weight).isEqualTo(0.0);
    }

    @Test
    void testReplaceWeightIfNull() {
        assertThat(
            handler.calculateSortingWeight(
                new ProductModel("3", "No Stock", 10, new StockModel()), new SortingCriteriaDTO(null, null)
            )
        )
        .isEqualTo(0.0);

        assertThat(
            handler.calculateSortingWeight(
                new ProductModel("4", "Default Weight", 10, new StockModel()), new SortingCriteriaDTO(2.0, null)
            )
        )
        .isGreaterThan(0.0);
    }
}
