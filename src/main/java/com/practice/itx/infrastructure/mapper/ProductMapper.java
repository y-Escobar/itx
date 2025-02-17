package com.practice.itx.infrastructure.mapper;

import com.practice.itx.application.dto.ListProductResponse;
import com.practice.itx.application.mapper.ProductMapperPort;
import com.practice.itx.domain.model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements ProductMapperPort {
    @Override
    public ListProductResponse productModelToListProductResponse(ProductModel productModel) {
        return new ListProductResponse(productModel.getId(), productModel.getName(), productModel.getSalesUnits(), productModel.getStock().getSizes());
    }
}
