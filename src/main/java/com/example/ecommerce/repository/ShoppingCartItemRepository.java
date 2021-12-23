package com.example.ecommerce.repository;

import com.example.ecommerce.model.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepository  extends JpaRepository<ShoppingCartItem,Long> {

}
