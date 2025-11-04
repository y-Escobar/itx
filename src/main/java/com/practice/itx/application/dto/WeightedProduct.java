package com.practice.itx.application.dto;

import com.practice.itx.domain.model.Product;

public record WeightedProduct(Product p, double score) {}