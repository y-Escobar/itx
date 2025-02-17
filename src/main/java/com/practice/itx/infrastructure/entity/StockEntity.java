package com.practice.itx.infrastructure.entity;

import com.practice.itx.domain.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stocks")
public class StockEntity {
    @MongoId
    private String id;
    private Map<Size, Integer> sizes;
}
