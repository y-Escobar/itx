package com.practice.itx.domain.port;

import com.practice.itx.domain.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepositoryPort {
    List<ProductModel> findProductsAndStock();
}
