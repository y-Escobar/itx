package com.practice.itx.infrastructure.entity;

import com.practice.itx.domain.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stocks")
public class StockEntity {
    @Id
    private String id;
    private Map<Size, Integer> sizes;
}
