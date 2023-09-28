package com.example.cartserver.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private String category;
    private String codeProduct;
    private float   price;
    private int availability;
}
