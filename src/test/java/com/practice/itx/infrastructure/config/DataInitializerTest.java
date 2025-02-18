package com.practice.itx.infrastructure.config;

import com.practice.itx.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Import({DataInitializer.class, MongoTemplate.class})
@SpringBootTest
public class DataInitializerTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private DataInitializer dataInitializer;

    @Test
    public void testRun() throws Exception {
        assertThat(mongoTemplate.findAll(ProductEntity.class)).isEmpty();

        dataInitializer.run();

        assertThat(mongoTemplate.findAll(ProductEntity.class)).isNotEmpty();
    }
}

