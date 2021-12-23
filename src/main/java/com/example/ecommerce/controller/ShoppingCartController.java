package com.example.ecommerce.controller;

import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable("id") Long id){
        ShoppingCart cart = shoppingCartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/usercarts")
    public ResponseEntity<Set<ShoppingCart>> getUserCarts(@RequestParam("id") Long id){
        Set<ShoppingCart> carts = shoppingCartService.getUserCarts(id);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }


    @GetMapping("/user/date")
    public ResponseEntity<ShoppingCart> getUserCartAtTime(@RequestParam("id") Long id,@RequestParam("date") LocalDateTime date){
        ShoppingCart cart = shoppingCartService.getUserCartAtTime(id,date);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ShoppingCart> addUser(@RequestParam("user_id") Long id){
        ShoppingCart cart = shoppingCartService.addCart(id);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        shoppingCartService.deleteCart(id);
        return new ResponseEntity<>("Cart deleted succesfully", HttpStatus.OK);
    }
}
