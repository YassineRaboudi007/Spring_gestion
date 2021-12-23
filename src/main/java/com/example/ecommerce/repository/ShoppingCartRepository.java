package com.example.ecommerce.repository;

import com.example.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<Set<ShoppingCart>> findShoppingCartByUser(Long id);
    Optional<ShoppingCart> findShoppingCartByUserAndDateTime(Long id, LocalDateTime date);
}
