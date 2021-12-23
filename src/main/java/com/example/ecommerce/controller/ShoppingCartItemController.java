package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddCartItemRequestBody;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.ShoppingCartItem;
import com.example.ecommerce.service.ShoppingCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/cartitem")
@RequiredArgsConstructor
public class ShoppingCartItemController {
    private final ShoppingCartItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartItem> getCartItem(@PathVariable("id") Long id){
        ShoppingCartItem cartItem = itemService.getCartItem(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ShoppingCartItem> addUser(@RequestBody AddCartItemRequestBody itemRequestBody){
        ShoppingCartItem cartItem = itemService.addCartItem(itemRequestBody);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        itemService.deleteCartItem(id);
        return new ResponseEntity<>("Cart deleted succesfully", HttpStatus.OK);
    }
}
