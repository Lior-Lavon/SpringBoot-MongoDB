package com.example.mongodb.converter;

import com.example.mongodb.command.ProductCommand;
import com.example.mongodb.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {
    @Override
    public Product convert(ProductCommand productCommand) {

        Product product = new Product();
        product.setDescription(productCommand.getDescription());
        if(productCommand.getId()!=null && !productCommand.getId().isBlank())
            product.setId(new ObjectId(productCommand.getId()));
        product.setImageUrl(productCommand.getImageUrl());
        product.setPrice(productCommand.getPrice());

        return product;
    }
}
