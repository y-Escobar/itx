package com.practice.itx.domain.model;

import com.practice.itx.domain.exceptions.ProductIllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductModelTest {

    @Test
    void testSetSalesUnitsWithValidValue() {
        ProductModel product = new ProductModel();
        product.setSalesUnits(100);
        assertThat(product.getSalesUnits()).isEqualTo(100);
    }

    @Test
    void testSetSalesUnitsWithNegativeValueShouldThrowException() {
        ProductModel product = new ProductModel();
        assertThatThrownBy(() -> product.setSalesUnits(-1))
                .isInstanceOf(ProductIllegalArgumentException.class)
                .hasMessage("Sales units cannot be negative");
    }

    @Test
    void testCalculateUnitsSalesWeight() {
        ProductModel product = new ProductModel();
        product.setSalesUnits(50);
        double weight = product.calculateUnitsSalesWeight(2.0);
        assertThat(weight).isEqualTo(100.0);
    }
}