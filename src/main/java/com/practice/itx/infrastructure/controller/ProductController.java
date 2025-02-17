package com.practice.itx.infrastructure.controller;

import com.practice.itx.application.ProductService;
import com.practice.itx.application.dto.ListProductResponse;
import com.practice.itx.application.dto.SortingCriteriaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/criteria")
    ResponseEntity<List<ListProductResponse>> findProductsByCriteria(@RequestBody SortingCriteriaDTO sortingCriteria){
        List<ListProductResponse> productList = productService.findProductsBySortingCriteria(sortingCriteria);
        return ResponseEntity.ok(productList);
    }

}
