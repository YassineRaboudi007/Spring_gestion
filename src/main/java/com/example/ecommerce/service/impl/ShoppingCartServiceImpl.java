package com.example.ecommerce.service.impl;

import com.example.ecommerce.exception.MyException;
import com.example.ecommerce.model.AppUser;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.repository.AppUserRepository;
import com.example.ecommerce.repository.ShoppingCartRepository;
import com.example.ecommerce.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart getCart(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(()-> new MyException("Cart not found"));

    }

    private final AppUserRepository appUserRepository;

    @Override
    public ShoppingCart addCart(Long id) {
        ShoppingCart cart = new ShoppingCart();
        AppUser user = appUserRepository.findById(id).orElseThrow(()->new MyException("UserNotFound"));
        cart.setUser(user);
        cart.setDateTime(LocalDateTime.now());
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        ShoppingCart cart = shoppingCartRepository.findById(id).orElseThrow(()->new MyException("Cart not found"));
        shoppingCartRepository.delete(cart);
    }

    @Override
    public Set<ShoppingCart> getUserCarts(Long userId) {
        return shoppingCartRepository.findShoppingCartByUser(userId)
                .orElseThrow(()->new MyException("User Not found"));
    }

    @Override
    public ShoppingCart getUserCartAtTime(Long userId, LocalDateTime date) {
        return shoppingCartRepository.findShoppingCartByUserAndDateTime(userId,date)
                .orElseThrow(()->new MyException("User Not found"));
    }
}
