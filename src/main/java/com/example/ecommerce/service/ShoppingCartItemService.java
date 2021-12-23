package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddCartItemRequestBody;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.model.AppUser;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.ShoppingCartItem;

public interface ShoppingCartItemService {
    ShoppingCartItem addCartItem(AddCartItemRequestBody itemRequestBody);
    void deleteCartItem(Long id);
    ShoppingCartItem getCartItem(Long id);
    ShoppingCartItem updateCartItem(ShoppingCartItem item);

}




