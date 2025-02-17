package com.practice.itx.application.port;

import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.model.Size;

import java.util.Map;

public interface ProductSortingCriteriaPort {
    double calculateSortingWeight(ProductModel product, SortingCriteriaDTO sortingCriteriaDTO);
}
