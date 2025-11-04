package com.practice.itx.application;

import com.practice.itx.application.dto.ProductsResponse;
import com.practice.itx.application.dto.WeightsRequest;
import com.practice.itx.domain.model.Product;
import com.practice.itx.domain.model.Size;
import com.practice.itx.domain.model.Stock;
import com.practice.itx.infrastructure.persistence.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SortProductTest {

    @Mock ProductRepository productRepository;
    @Mock Map<String, ToDoubleFunction<Product>> criteria;
    SortProduct sortProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sortProduct = new SortProduct(productRepository, criteria);
    }

    @Test
    void testFindProductsBySortingCriteria() {
        var p1 = new Product("1", "Product A", 100, new Stock("s1", Map.of(Size.S,10, Size.M,5, Size.L,10)));
        var p2 = new Product("2", "Product B", 50,  new Stock("s2", Map.of(Size.S,5,  Size.M,3, Size.L,10)));
        when(productRepository.findProductsAndStock()).thenReturn(List.of(p1, p2));

        ProductsResponse result = sortProduct.execute(new WeightsRequest(1.0, 1.0).asMap());

        assertThat(result.products()).hasSize(2);

        Product first = result.products().getFirst();
        assertThat(first.getId()).isEqualTo("1");
        assertThat(first.getName()).isNotBlank();
        assertThat(first.getStock()).isNotNull();
        assertThat(first.getStock().getSizes()).isNotEmpty();

        verify(productRepository, times(1)).findProductsAndStock();
    }

    @Test
    void testFindProductsBySortingCriteriaWithEmptyList() {
        when(productRepository.findProductsAndStock()).thenReturn(List.of());

        ProductsResponse result = sortProduct.execute(new WeightsRequest(1.0, 1.0).asMap());

        assertThat(result.products()).isEmpty();
        verify(productRepository, times(1)).findProductsAndStock();
    }
}
