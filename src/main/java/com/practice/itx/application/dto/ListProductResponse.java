package com.practice.itx.application.dto;

import com.practice.itx.domain.model.Size;

import java.util.Map;

public record ListProductResponse(String id, String name, Integer salesUnits, Map<Size, Integer> stock) {}

