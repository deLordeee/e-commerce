package com.example.e_commerce.service;

import com.example.e_commerce.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    void deleteOrder(Long id);

    Order updateOrder(Long id, Order updatedOrder);
}
