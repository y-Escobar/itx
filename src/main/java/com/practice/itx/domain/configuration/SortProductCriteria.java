package com.practice.itx.domain.configuration;

import com.practice.itx.domain.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.ToDoubleFunction;

@Configuration
public class SortProductCriteria {
    @Bean(name = "sales_units")
    public ToDoubleFunction<Product> salesUnits() { return Product::getSalesUnits; }

    @Bean(name = "stock_ratio")
    public ToDoubleFunction<Product> stockRatio() {
        return p -> {
            var s = p.getStock();
            if (s == null || s.getSizes().isEmpty()) return 0d;
            double withStock = s.getSizes().values().stream().filter(v -> v > 0).count();
            return withStock / s.getSizes().size();
        };
    }
}