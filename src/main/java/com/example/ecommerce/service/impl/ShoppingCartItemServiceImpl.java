package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.AddCartItemRequestBody;
import com.example.ecommerce.exception.MyException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.ShoppingCartItem;
import com.example.ecommerce.repository.ShoppingCartItemRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.ShoppingCartItemService;
import com.example.ecommerce.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ProductService productService;
    private final ShoppingCartService cartService;

    @Override
    public ShoppingCartItem getCartItem(Long id) {
        return shoppingCartItemRepository.findById(id).orElseThrow(()->new MyException("Cart Not found"));
    }

    @Override
    public ShoppingCartItem addCartItem(AddCartItemRequestBody itemRequestBody) {
        Product product = productService.findProductById(itemRequestBody.getProductId());
        ShoppingCart cart = cartService.getCart(itemRequestBody.getCartId());
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(itemRequestBody.getQuantity());
        return shoppingCartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        ShoppingCartItem item = getCartItem(id);
        shoppingCartItemRepository.delete(item);
    }



    @Override
    public ShoppingCartItem updateCartItem(ShoppingCartItem item) {
        ShoppingCartItem cartItem =getCartItem(item.getId());
        cartItem.setCart(item.getCart());
        cartItem.setProduct(item.getProduct());
        cartItem.setQuantity(item.getQuantity());
        return shoppingCartItemRepository.save(cartItem);
    }
}
