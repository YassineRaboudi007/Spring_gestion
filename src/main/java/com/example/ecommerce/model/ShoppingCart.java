package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false,referencedColumnName = "id" )
    private AppUser user;

    @JsonIgnore
    @OneToMany(mappedBy = "id")
    private Set<ShoppingCartItem> carts;

    private LocalDateTime  dateTime;
}
