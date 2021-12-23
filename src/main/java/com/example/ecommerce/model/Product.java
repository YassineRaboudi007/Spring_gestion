package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private Integer quantity;
    private float price;
    private Instant createdAt;
    private Instant updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private Set<ShoppingCartItem> cartItems;
}
