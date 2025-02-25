package com.practice.itx.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockModel {

    private String id;
    private Map<Size, Integer> sizes = new HashMap<>();

    public void setSizes(Map<Size, Integer> sizes) {
        this.sizes = (sizes != null) ? new HashMap<>(sizes) : Collections.emptyMap();
    }

    public double calculateStockRatioWeight(double weight) {
        int totalSizes = sizes.size();
        if (totalSizes == 0) return 0.0;
        int sizesWithStock = (int) sizes.values().stream().filter(v -> v > 0).count();
        return ((double) sizesWithStock / totalSizes) * weight;
    }

}
