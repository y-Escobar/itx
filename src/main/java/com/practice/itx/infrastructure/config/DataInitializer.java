package com.practice.itx.infrastructure.config;

import com.practice.itx.domain.model.Size;
import com.practice.itx.infrastructure.entity.ProductEntity;
import com.practice.itx.infrastructure.entity.StockEntity;
import com.practice.itx.infrastructure.persistence.ProductRepository;
import com.practice.itx.infrastructure.persistence.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@Profile({"dev", "test"})
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    private final List<String> productIds = List.of(
            "4945ee60-ea5d-4466-b9eb-0c97bcf9e959",
            "4bd92219-9f51-4812-8512-ee512003dc70",
            "3caa1263-2d6f-4fc0-99c0-ad213b1e8097",
            "9ce84648-5c5c-45b4-bbda-9a23f03a7df7",
            "3d21ffa2-1abc-4e5a-ba95-515773330cf0",
            "b7d2f0d5-c7aa-4fff-8bfe-c87a2898e4f0"
    );

    private final List<String> stockIds = List.of(
            "db3372fa-e616-43af-8168-fb8f42d98d97",
            "0dad128f-8c0b-482e-9cc2-119524de2d64",
            "f68e2e3b-58dd-462f-85a6-c1cbec1283b8",
            "ba22a3d3-a592-48d7-85e0-0080fb380e41",
            "2546f3f9-8ae5-46bc-943b-80594d43790f",
            "ee2b520c-5d63-4536-ae22-d2d8da9e1d35"
    );

    public DataInitializer(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(String... args) {

        log.info("Executing DataInitializer...");

        stockRepository.deleteAllById(stockIds);
        productRepository.deleteAllById(productIds);

        log.info("Deleting previous entries");

        List<StockEntity> initialStock = getStockEntities();
        log.info("Saving Stock list");
        stockRepository.saveAll(initialStock);
        log.info("Saved Stock list");

        List<ProductEntity> initialProducts = getProductEntities(initialStock);
        log.info("Saving Product list");
        productRepository.saveAll(initialProducts);
        log.info("Saved Product list");

    }

    private List<ProductEntity> getProductEntities(List<StockEntity> initialStock) {
        int i = 0, j = 0;
        return List.of(
            new ProductEntity(productIds.get(i++), "V-NECH BASIC SHIRT", 100, initialStock.get(j++).getId()),
            new ProductEntity(productIds.get(i++), "CONTRASTING FABRIC T-SHIRT", 50, initialStock.get(j++).getId()),
            new ProductEntity(productIds.get(i++), "RAISED PRINT T-SHIRT", 80, initialStock.get(j++).getId()),
            new ProductEntity(productIds.get(i++), "PLEATED T-SHIRT", 3, initialStock.get(j++).getId()),
            new ProductEntity(productIds.get(i++), "CONTRASTING LACE T-SHIRT", 650, initialStock.get(j++).getId()),
            new ProductEntity(productIds.get(i), "SLOGAN T-SHIRT", 20, initialStock.get(j).getId())
        );
    }

    private List<StockEntity> getStockEntities() {
        int i = 0;
        return List.of(
            new StockEntity(stockIds.get(i++), new LinkedHashMap<>(Map.of(Size.S, 4, Size.M, 9, Size.L, 0))),
            new StockEntity(stockIds.get(i++), new LinkedHashMap<>(Map.of(Size.S, 35, Size.M, 9, Size.L, 9))),
            new StockEntity(stockIds.get(i++), new LinkedHashMap<>(Map.of(Size.S, 20, Size.M, 2, Size.L, 20))),
            new StockEntity(stockIds.get(i++), new LinkedHashMap<>(Map.of(Size.S, 25, Size.M, 30, Size.L, 10))),
            new StockEntity(stockIds.get(i++), new LinkedHashMap<>(Map.of(Size.S, 0, Size.M, 1, Size.L, 0))),
            new StockEntity(stockIds.get(i), new LinkedHashMap<>(Map.of(Size.S, 9, Size.M, 2, Size.L, 5)))
        );
    }
}
