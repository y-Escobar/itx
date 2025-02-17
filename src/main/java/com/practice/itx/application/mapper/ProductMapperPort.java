package com.practice.itx.application.mapper;

import com.practice.itx.application.dto.ListProductResponse;
import com.practice.itx.domain.model.ProductModel;

public interface ProductMapperPort {
    ListProductResponse productModelToListProductResponse(ProductModel productModel);
}
