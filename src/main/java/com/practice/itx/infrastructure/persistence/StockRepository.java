package com.practice.itx.infrastructure.persistence;

import com.practice.itx.domain.port.StockRepositoryPort;
import com.practice.itx.infrastructure.entity.StockEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends MongoRepository<StockEntity, String>, StockRepositoryPort {}
