package com.practice.itx.application;

import com.practice.itx.application.dto.ListProductResponse;
import com.practice.itx.application.dto.SortingCriteriaDTO;
import com.practice.itx.application.mapper.ProductMapperPort;
import com.practice.itx.application.port.ProductSortingCriteriaPort;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.port.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepositoryPort productRepositoryPort;
    private final ProductSortingCriteriaPort productSortingCriteriaPort;
    private final ProductMapperPort productMapperPort;

    ProductService(
            ProductRepositoryPort productRepositoryPort,
            ProductSortingCriteriaPort productSortingCriteriaPort,
            ProductMapperPort productMapperPort
    ){
        this.productRepositoryPort = productRepositoryPort;
        this.productSortingCriteriaPort = productSortingCriteriaPort;
        this.productMapperPort = productMapperPort;
    }

    public List<ListProductResponse> findProductsBySortingCriteria(SortingCriteriaDTO sortingCriteriaDTO){
        List<ProductModel> productModelList = productRepositoryPort.findProductsAndStock();

        return productModelList.stream()
                .sorted(Comparator.comparingDouble((ProductModel p) -> productSortingCriteriaPort.calculateSortingWeight(p, sortingCriteriaDTO))
                        .thenComparing(ProductModel::getName)
                        .reversed())
                .map(productMapperPort::productModelToListProductResponse)
                .toList();
    }
}

