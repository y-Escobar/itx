package com.practice.itx.application;

import com.practice.itx.application.dto.ProductsResponse;
import com.practice.itx.application.dto.WeightsRequest;
import com.practice.itx.application.dto.WeightedProduct;
import com.practice.itx.domain.model.Product;
import com.practice.itx.infrastructure.persistence.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;

@Service
@Slf4j
public class SortProduct {

    private final ProductRepository productRepository;
    private final Map<String, ToDoubleFunction<Product>> criteria;

    public SortProduct(ProductRepository productRepository, Map<String, ToDoubleFunction<Product>> criteria) {
        this.productRepository = productRepository;
        this.criteria = new LinkedHashMap<>(criteria);
    }

    public final ProductsResponse execute(Map<String, Double> weights){
        List<Product> products = productRepository.findProductsAndStock();

        List<WeightedProduct> weightedProducts = products.stream()
                .map(p -> new WeightedProduct(p, scoreOf(p, weights)))
                .sorted(Comparator
                        .comparingDouble(WeightedProduct::score).reversed()
                        .thenComparing(wp -> wp.p().getName(), String.CASE_INSENSITIVE_ORDER))
                .toList();

        return new ProductsResponse(weightedProducts.stream().map(WeightedProduct::p).toList());
    }

    private double scoreOf(Product p, Map<String, Double> weights) {
        return weights.entrySet().stream()
            .mapToDouble(e ->{
                ToDoubleFunction<Product> function = criteria.get(e.getKey());
                if(function == null || e.getValue() == null) {
                    log.warn("Unknown criteria {} - Returning with value 0d", e.getKey());
                    return 0d;
                }
                try{
                    return function.applyAsDouble(p) * e.getValue();
                }catch(NumberFormatException notADouble){
                    log.warn("Criteria {} with not a Double value - Val: {}", e.getKey(), e.getValue());
                    return 0d;
                }
            })
            .sum();
    }
}