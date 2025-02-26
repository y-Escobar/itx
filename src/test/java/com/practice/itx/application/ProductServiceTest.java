package com.practice.itx.application;

import com.practice.itx.application.dto.ListProductResponse;
import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.application.mapper.ProductMapperPort;
import com.practice.itx.application.port.ProductSortingCriteriaPort;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.model.Size;
import com.practice.itx.domain.model.StockModel;
import com.practice.itx.domain.port.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private ProductSortingCriteriaPort productSortingCriteriaPort;

    @Mock
    private ProductMapperPort productMapperPort;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProductsBySortingCriteria() {
        ProductModel product1 = new ProductModel("1", "Product A", 100, new StockModel("1", Map.of(Size.S, 10, Size.M, 5, Size.L, 10)));
        ProductModel product2 = new ProductModel("2", "Product B", 50, new StockModel("2", Map.of(Size.S, 5, Size.M, 3, Size.L, 10)));
        List<ProductModel> productList = List.of(product1, product2);

        SortingCriteriaDTO sortingCriteria = new SortingCriteriaDTO(1.0, 1.0);

        when(productRepositoryPort.findProductsAndStock()).thenReturn(productList);
        when(productSortingCriteriaPort.calculateSortingWeight(product1, sortingCriteria)).thenReturn(101.0);
        when(productSortingCriteriaPort.calculateSortingWeight(product2, sortingCriteria)).thenReturn(51.0);
        when(productMapperPort.productModelToListProductResponse(product1)).thenReturn(new ListProductResponse("1", "Product A", 100, Map.of(Size.S, 10, Size.M, 5, Size.L, 10)));
        when(productMapperPort.productModelToListProductResponse(product2)).thenReturn(new ListProductResponse("2", "Product B", 50, Map.of(Size.S, 5, Size.M, 3, Size.L, 10)));

        List<ListProductResponse> result = productService.findProductsBySortingCriteria(sortingCriteria);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo("1");
        assertThat(result.get(1).id()).isEqualTo("2");

        verify(productRepositoryPort, times(1)).findProductsAndStock();
        verify(productSortingCriteriaPort, times(productList.size())).calculateSortingWeight(any(), eq(sortingCriteria));
        verify(productMapperPort, times(2)).productModelToListProductResponse(any());
    }

    @Test
    void testFindProductsBySortingCriteriaWithEmptyList() {
        SortingCriteriaDTO sortingCriteria = new SortingCriteriaDTO(1.0, 1.0);
        when(productRepositoryPort.findProductsAndStock()).thenReturn(List.of());

        List<ListProductResponse> result = productService.findProductsBySortingCriteria(sortingCriteria);

        assertThat(result).isEmpty();
        verify(productRepositoryPort, times(1)).findProductsAndStock();
    }

}