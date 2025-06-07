package com.example.e_commerce.service.impl;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.repository.CartRepository;
import com.example.e_commerce.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.function.Consumer;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public Cart checkIfExist(Long id){
        Cart cart = cartRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Cart does not exist!"));
        return cart;
    }
    @Override
    public Cart createCart(Cart cart){
        int quantity = cart.getProductIds().size();
        cart.setTotalItems(quantity);
        return cartRepository.save(cart);
    }
    @Override
    public Cart getCartById(Long id){

        Cart cart = checkIfExist(id);
        return cart;
    }
    @Override
    public List<Cart> getAllCarts(){
        List<Cart> carts = cartRepository.findAll();
        return carts;
    }
    @Override
    public void deleteCart(Long id){
        checkIfExist(id);
        cartRepository.deleteById(id);
    }
    @Override
    public Cart updateCart(Long id , @RequestBody Cart updatedCart){
        Cart cart = checkIfExist(id);
        if(updatedCart.getTotalItems() < 0){
            throw  new IllegalArgumentException("Wrong number of products!");
        }

        else if(updatedCart.getTotalItems() == 0){
            cart.setTotalPrice(0.0);
            cart.setProductIds(null);
            cart.setTotalItems(0);
        }
        else{
            cart.setTotalItems(updatedCart.getTotalItems());
            cart.setTotalPrice(updatedCart.getTotalPrice());
            cart.setProductIds(updatedCart.getProductIds());
        }

        cartRepository.save(cart);
        return cart;
    }

    private <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
