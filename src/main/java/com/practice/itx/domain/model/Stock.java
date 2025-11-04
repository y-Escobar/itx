package com.practice.itx.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class Stock {

    private String id;
    @Getter
    private Map<Size, Integer> sizes = Map.of();

    public Stock(String id, Map<Size, Integer> sizes) {
        this.id = id;
        setSizes(sizes);
    }

    public void setSizes(Map<Size, Integer> sizes) {
        this.sizes = (sizes == null) ? Map.of() : Map.copyOf(sizes);
    }

}