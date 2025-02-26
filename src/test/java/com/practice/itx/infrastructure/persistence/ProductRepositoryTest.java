package com.practice.itx.infrastructure.persistence;

import com.practice.itx.config.MongoTestConfig;
import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.model.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Import(MongoTestConfig.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindProductsAndStock() {
        List<ProductModel> products = productRepository.findProductsAndStock();

        assertThat(products).isNotEmpty();
        assertThat(products.getFirst().getId()).isEqualTo("4945ee60-ea5d-4466-b9eb-0c97bcf9e959");
        assertThat(products.getFirst().getName()).isEqualTo("V-NECH BASIC SHIRT");
        assertThat(products.getFirst().getSalesUnits()).isEqualTo(100);
        assertThat(products.getFirst().getStock().getId()).isEqualTo("db3372fa-e616-43af-8168-fb8f42d98d97");
        assertThat(products.getFirst().getStock().getSizes()).isEqualTo(Map.of(Size.S, 4, Size.M, 9, Size.L, 0));
    }
}