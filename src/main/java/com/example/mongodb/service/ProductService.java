package com.example.mongodb.service;

import com.example.mongodb.command.ProductCommand;
import com.example.mongodb.domain.Product;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface ProductService {

    ProductCommand save(ProductCommand productCommand);

    Optional<ProductCommand> findById(ObjectId Id);

    Iterable<Product> findAll();

    void deleteById(String Id);

    void delete(ProductCommand product);

}
