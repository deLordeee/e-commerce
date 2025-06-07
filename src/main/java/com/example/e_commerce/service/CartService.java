package com.example.e_commerce.service;

import com.example.e_commerce.entity.Cart;

import java.util.List;

public interface CartService {

    Cart createCart(Cart cart);

    Cart getCartById(Long id);

    List<Cart> getAllCarts();

    void deleteCart(Long id);

    Cart updateCart(Long id ,  Cart cart);
}
