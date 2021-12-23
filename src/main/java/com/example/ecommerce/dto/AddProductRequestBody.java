package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddProductRequestBody {
    private String name;
    private Integer quantity;
    private float price;
}
