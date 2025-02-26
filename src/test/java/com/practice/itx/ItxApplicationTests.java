package com.practice.itx;

import com.practice.itx.config.MongoTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(MongoTestConfig.class)
class ItxApplicationTests {

	@Test
	void contextLoads() {
	}

}