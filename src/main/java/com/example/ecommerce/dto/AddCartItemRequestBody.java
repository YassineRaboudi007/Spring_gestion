package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddCartItemRequestBody {
    private Long cartId;
    private Long productId;
    private Integer quantity;

}
