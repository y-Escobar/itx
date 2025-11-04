package com.practice.itx.application.dto;

import java.util.HashMap;
import java.util.Map;

public record WeightsRequest(
        Double salesUnits,
        Double stockRatio
) {
    public Map<String, Double> asMap() {
            var map = new HashMap<String, Double>();
        if (salesUnits != null)  map.put("sales_units", salesUnits);
        if (stockRatio != null)  map.put("stock_ratio", stockRatio);
        return Map.copyOf(map);
    }
}
