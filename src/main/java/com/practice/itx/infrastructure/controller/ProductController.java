package com.practice.itx.infrastructure.controller;

import com.practice.itx.application.SortProduct;
import com.practice.itx.application.dto.ProductsResponse;
import com.practice.itx.application.dto.WeightsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public final class ProductController {

    private final SortProduct sortProducts;

    ProductController(SortProduct sortProducts){
        this.sortProducts = sortProducts;
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> list(@Validated @ModelAttribute WeightsRequest weights) {
        return ResponseEntity.ok(sortProducts.execute(weights.asMap()));
    }

}
