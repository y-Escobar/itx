package com.practice.itx.infrastructure.mapper;

import com.practice.itx.application.dto.ListProductResponse;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.model.Size;
import com.practice.itx.domain.model.StockModel;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void testProductModelToListProductResponse() {

        StockModel stock = new StockModel("1", Map.of(Size.S, 10, Size.M, 5, Size.L, 0));
        ProductModel productModel = new ProductModel("1", "Test Product", 100, stock);

        ListProductResponse response = productMapper.productModelToListProductResponse(productModel);

        assertThat(response.id()).isEqualTo("1");
        assertThat(response.name()).isEqualTo("Test Product");
        assertThat(response.salesUnits()).isEqualTo(100);
        assertThat(response.stock()).containsEntry(Size.S, 10).containsEntry(Size.M, 5).containsEntry(Size.L, 0);
    }

    @Test
    void testProductModelToListProductResponseWithEmptyStock() {
        StockModel emptyStock = new StockModel();
        ProductModel productModel = new ProductModel("2", "No Stock Product", 50, emptyStock);

        ListProductResponse response = productMapper.productModelToListProductResponse(productModel);

        assertThat(response.id()).isEqualTo("2");
        assertThat(response.name()).isEqualTo("No Stock Product");
        assertThat(response.salesUnits()).isEqualTo(50);
        assertThat(response.stock()).isEmpty();
    }
}