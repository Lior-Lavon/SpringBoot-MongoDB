package com.example.mongodb.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Product {

    @Id
    private ObjectId id;
    private String description;
    private Long price;
    private String imageUrl;


}
