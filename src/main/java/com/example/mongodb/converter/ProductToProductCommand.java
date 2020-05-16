package com.example.mongodb.converter;

import com.example.mongodb.command.ProductCommand;
import com.example.mongodb.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {
    @Override
    public ProductCommand convert(Product product) {
        ProductCommand productCommand = new ProductCommand();

        productCommand.setDescription(product.getDescription());
        productCommand.setId(product.getId().toHexString());
        productCommand.setImageUrl(product.getImageUrl());
        productCommand.setPrice(product.getPrice());

        return productCommand;
    }
}
