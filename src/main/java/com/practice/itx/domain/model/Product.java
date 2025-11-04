package com.practice.itx.domain.model;

import com.practice.itx.domain.exceptions.ProductIllegalArgumentException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private int salesUnits = 0;
    private Stock stock = new Stock();

    public void setSalesUnits(int salesUnits) {
        if (salesUnits < 0) {
            throw new ProductIllegalArgumentException("Sales units cannot be negative");
        }
        this.salesUnits = salesUnits;
    }

    public void setStock(Stock stock) {
        this.stock = (stock == null) ? new Stock() : stock;
    }
}