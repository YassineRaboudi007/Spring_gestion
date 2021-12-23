package com.example.ecommerce.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Data
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="shoppingcart_id")
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

}
