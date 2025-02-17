package com.practice.itx.application.handler;

import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.application.port.ProductSortingCriteriaPort;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.model.Size;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductSortingCriteriaHandler implements ProductSortingCriteriaPort {

    @Override
    public double calculateSortingWeight(ProductModel product, SortingCriteriaDTO sortingCriteriaDTO) {
        double weight = 0;
        weight += calculateUnitsSalesWeight(replaceWeightIfNull(sortingCriteriaDTO.salesUnitsWeight()), product.getSalesUnits());
        weight += calculateStockRatioWeight(replaceWeightIfNull(sortingCriteriaDTO.stockRatioWeight()), product.getStock().getSizes());

        System.out.printf("Puntuación para el producto %s es de %.2f%n", product.getName(), weight);

        return weight;
    }

    private double calculateUnitsSalesWeight(double weight, int salesUnits) {
        return salesUnits * weight;
    }

    private double calculateStockRatioWeight(double weight, Map<Size, Integer> sizes) {
        int totalSizes = sizes.size();
        int sizesWithStock = (int) sizes.values().stream().filter(v -> v > 0).count();
        return ((double) sizesWithStock / totalSizes) * weight;
    }

    private double replaceWeightIfNull(Double weight) {
        return (weight != null) ? weight : 0.0;
    }
}
