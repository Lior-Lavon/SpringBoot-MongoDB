package com.example.mongodb.repository;

import com.example.mongodb.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, ObjectId> {
}
