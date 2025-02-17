package com.practice.itx.application.handler;

import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.application.port.ProductSortingCriteriaPort;
import com.practice.itx.domain.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductSortingCriteriaHandler implements ProductSortingCriteriaPort {

    @Override
    public double calculateSortingWeight(ProductModel product, SortingCriteriaDTO sortingCriteriaDTO) {
        double weight = 0;
        weight += product.calculateUnitsSalesWeight(replaceWeightIfNull(sortingCriteriaDTO.salesUnitsWeight()));
        weight += product.getStock().calculateStockRatioWeight(replaceWeightIfNull(sortingCriteriaDTO.salesUnitsWeight()));
        return weight;
    }

    private double replaceWeightIfNull(Double weight) {
        return (weight != null) ? weight : 0.0;
    }
}
