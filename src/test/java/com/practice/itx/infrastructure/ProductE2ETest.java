package com.practice.itx.infrastructure;

import com.practice.itx.application.dto.ProductsResponse;
import com.practice.itx.config.MongoTestConfig;
import com.practice.itx.domain.model.Product;
import com.practice.itx.domain.model.Size;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(MongoTestConfig.class)
public class ProductE2ETest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Test that verifies that data is loaded")
    void testFindProductsByCriteria1() {
        ProductsResponse productList = given()
                .port(port)
                .contentType("application/json")
                .param("sales_units", "1.0")
                .param("stock_ratio", "1.0")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .headers("Content-Type", equalTo("application/json"))
                .extract()
                .body()
                .as(ProductsResponse.class);

        assertThat(productList.products()).isNotEmpty();

        assertThat(productList.products()).extracting("id", "name", "salesUnits", "stock.sizes")
                .contains(
                        new Tuple("3d21ffa2-1abc-4e5a-ba95-515773330cf0", "CONTRASTING LACE T-SHIRT", 650, Map.of(Size.S, 0, Size.M, 1, Size.L, 0)),
                        new Tuple("4945ee60-ea5d-4466-b9eb-0c97bcf9e959", "V-NECH BASIC SHIRT", 100, Map.of(Size.S, 4, Size.M, 9, Size.L, 0)),
                        new Tuple("3caa1263-2d6f-4fc0-99c0-ad213b1e8097", "RAISED PRINT T-SHIRT", 80, Map.of(Size.S, 20, Size.M, 2, Size.L, 20)),
                        new Tuple("4bd92219-9f51-4812-8512-ee512003dc70", "CONTRASTING FABRIC T-SHIRT", 50, Map.of(Size.S, 35, Size.M, 9, Size.L, 9)),
                        new Tuple("b7d2f0d5-c7aa-4fff-8bfe-c87a2898e4f0", "SLOGAN T-SHIRT", 20, Map.of(Size.S, 9, Size.M, 2, Size.L, 5)),
                        new Tuple("9ce84648-5c5c-45b4-bbda-9a23f03a7df7", "PLEATED T-SHIRT", 3, Map.of(Size.S, 25, Size.M, 30, Size.L, 10))
                );
    }

    @Test
    @DisplayName("Test that verifies the product order based of Sorting Criteria where UnitsSales weighs more than StockRatio")
    void testFindProductsByCriteria2() {
        ProductsResponse productList = given()
                .port(port)
                .contentType("application/json")
                .param("sales_units", "1.0")
                .param("stock_ratio", "0.0")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .headers("Content-Type", equalTo("application/json"))
                .extract()
                .body()
                .as(ProductsResponse.class);

        assertThat(productList.products()).isNotEmpty();

        assertThat(productList.products()).extracting("id")
                .containsSequence(
                        "3d21ffa2-1abc-4e5a-ba95-515773330cf0",
                        "4945ee60-ea5d-4466-b9eb-0c97bcf9e959",
                        "3caa1263-2d6f-4fc0-99c0-ad213b1e8097",
                        "4bd92219-9f51-4812-8512-ee512003dc70",
                        "b7d2f0d5-c7aa-4fff-8bfe-c87a2898e4f0",
                        "9ce84648-5c5c-45b4-bbda-9a23f03a7df7"
                );
    }

    @Test
    @DisplayName("Test that verifies the product order based of Sorting Criteria where StockRatio weighs way more than UnitsSales")
    void testFindProductsByCriteria3() {
        ProductsResponse productList = given()
                .port(port)
                .contentType("application/json")
                .param("sales_units", "0.01")
                .param("stock_ratio", "100.0")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .headers("Content-Type", equalTo("application/json"))
                .extract()
                .body()
                .as(ProductsResponse.class);

        assertThat(productList.products()).isNotEmpty();

        assertThat(productList.products()).extracting("id")
                .containsSequence(
                        "3caa1263-2d6f-4fc0-99c0-ad213b1e8097",
                        "4bd92219-9f51-4812-8512-ee512003dc70",
                        "b7d2f0d5-c7aa-4fff-8bfe-c87a2898e4f0",
                        "9ce84648-5c5c-45b4-bbda-9a23f03a7df7",
                        "4945ee60-ea5d-4466-b9eb-0c97bcf9e959",
                        "3d21ffa2-1abc-4e5a-ba95-515773330cf0"
                );
    }
}
