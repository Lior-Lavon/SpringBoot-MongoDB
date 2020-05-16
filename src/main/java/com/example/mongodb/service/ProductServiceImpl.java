package com.example.mongodb.service;

import com.example.mongodb.command.ProductCommand;
import com.example.mongodb.converter.ProductCommandToProduct;
import com.example.mongodb.converter.ProductToProductCommand;
import com.example.mongodb.domain.Product;
import com.example.mongodb.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductToProductCommand productToProductCommand;
    private ProductCommandToProduct productCommandToProduct;

    public ProductServiceImpl(ProductRepository productRepository, ProductToProductCommand productToProductCommand, ProductCommandToProduct productCommandToProduct) {
        this.productRepository = productRepository;
        this.productToProductCommand = productToProductCommand;
        this.productCommandToProduct = productCommandToProduct;
    }

    @Override
    public ProductCommand save(ProductCommand productCommand) {
        Product product = productCommandToProduct.convert(productCommand);
        Product savedProduct = productRepository.save(product);
        return productToProductCommand.convert(savedProduct);
    }

    @Override
    public Optional<ProductCommand> findById(ObjectId Id) {
        Optional<Product> optionalProduct = productRepository.findById(Id);
        if(optionalProduct.isPresent()){
            return Optional.of(productToProductCommand.convert(optionalProduct.get()));
        }
        return null;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(String Id) {
        productRepository.deleteById(new ObjectId(Id));
    }

    @Override
    public void delete(ProductCommand productCommand) {
        productRepository.delete(productCommandToProduct.convert(productCommand));
    }
}
