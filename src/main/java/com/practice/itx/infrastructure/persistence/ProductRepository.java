package com.practice.itx.infrastructure.persistence;

import com.practice.itx.domain.model.ProductModel;
import com.practice.itx.domain.port.ProductRepositoryPort;
import com.practice.itx.infrastructure.entity.ProductEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String>, ProductRepositoryPort {

    @Override
    @Aggregation(pipeline = {
            "{ '$lookup': { 'from': 'stocks', 'localField': 'stockId', 'foreignField': '_id', 'as': 'stock' } }",
            "{ '$unwind': { 'path': '$stock', 'preserveNullAndEmptyArrays': true } }",
            "{ '$project': { 'id': '$_id', 'name': 1, 'salesUnits': 1, 'stock': { '_id': '$stock._id', 'sizes': '$stock.sizes' } } }"
    })
    List<ProductModel> findProductsAndStock();

}
