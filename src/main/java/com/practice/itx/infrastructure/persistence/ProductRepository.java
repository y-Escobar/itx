package com.practice.itx.infrastructure.persistence;

import com.practice.itx.domain.model.Product;
import com.practice.itx.infrastructure.entity.ProductEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    @Aggregation(pipeline = {
            "{ '$lookup': { 'from': 'stocks', 'localField': 'stockId', 'foreignField': '_id', 'as': 'stock' } }",
            "{ '$unwind': { 'path': '$stock', 'preserveNullAndEmptyArrays': true } }",
            "{ '$project': { 'id': '$_id', 'name': 1, 'salesUnits': 1, 'stock': { '_id': '$stock._id', 'sizes': '$stock.sizes' } } }"
    })
    List<Product> findProductsAndStock();

}
