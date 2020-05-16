package com.example.mongodb.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductCommand {

    private String id;
    private String description;
    private Long price;
    private String imageUrl;

}
