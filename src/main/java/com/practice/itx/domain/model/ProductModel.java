package com.practice.itx.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private String id;
    private String name;
    private int salesUnits = 0;
    private StockModel stock = new StockModel();

    public void setSalesUnits(int salesUnits) {
        if (salesUnits < 0) {
            throw new IllegalArgumentException("Sales units cannot be negative");
        }
        this.salesUnits = salesUnits;
    }
}
