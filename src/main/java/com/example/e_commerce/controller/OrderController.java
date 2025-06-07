package com.example.e_commerce.controller;

import com.example.e_commerce.entity.Order;
import com.example.e_commerce.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
       return new ResponseEntity<>( orderService.createOrder(order), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrderById(id) , HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders() , HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return HttpStatus.NO_CONTENT;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id , @RequestBody Order order){
        return new ResponseEntity<>(orderService.updateOrder(id , order) , HttpStatus.OK);
    }
}
