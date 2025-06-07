package com.example.e_commerce.controller;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private  CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){
        return new ResponseEntity<>(cartService.createCart(cart) , HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id){
        return new ResponseEntity<>(cartService.getCartById(id) , HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts(){
        return new ResponseEntity<>(cartService.getAllCarts() , HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCart(@PathVariable Long id){
        cartService.deleteCart(id);
        return HttpStatus.NO_CONTENT;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id , @RequestBody Cart cart){
        return new ResponseEntity<>(cartService.updateCart(id , cart) , HttpStatus.OK) ;

    }
}
