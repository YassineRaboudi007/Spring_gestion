package com.example.ecommerce.service;

import com.example.ecommerce.model.AppUser;
import com.example.ecommerce.model.ShoppingCart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface ShoppingCartService {
    ShoppingCart addCart(Long id);
    void deleteCart(Long id);
    Set<ShoppingCart> getUserCarts(Long userId);
    ShoppingCart getUserCartAtTime(Long userId, LocalDateTime date);
    ShoppingCart getCart(Long id);

}
