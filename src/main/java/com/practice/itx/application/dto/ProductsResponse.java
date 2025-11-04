package com.practice.itx.application.dto;

import com.practice.itx.domain.model.Product;

import java.util.List;

public record ProductsResponse(List<Product> products) {}