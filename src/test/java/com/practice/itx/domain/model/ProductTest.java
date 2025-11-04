package com.practice.itx.domain.model;

import com.practice.itx.application.dto.ProductsResponse;
import com.practice.itx.domain.exceptions.ProductIllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    void testSetSalesUnitsWithNegativeValueShouldThrowException() {
        Product product = new Product();
        assertThatThrownBy(() -> product.setSalesUnits(-1))
                .isInstanceOf(ProductIllegalArgumentException.class)
                .hasMessage("Sales units cannot be negative");
    }
}