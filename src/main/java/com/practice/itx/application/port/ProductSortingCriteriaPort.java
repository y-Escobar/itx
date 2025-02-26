package com.practice.itx.application.port;

import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.domain.model.ProductModel;

public interface ProductSortingCriteriaPort {
    double calculateSortingWeight(ProductModel product, SortingCriteriaDTO sortingCriteriaDTO);
}
