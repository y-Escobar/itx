package com.practice.itx.application;

import com.practice.itx.domain.port.StockRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final StockRepositoryPort stockRepository;

    public StockService(StockRepositoryPort stockRepository) {
        this.stockRepository = stockRepository;
    }
}

