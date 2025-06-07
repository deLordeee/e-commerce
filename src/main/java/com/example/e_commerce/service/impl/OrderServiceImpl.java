package com.example.e_commerce.service.impl;

import com.example.e_commerce.entity.Order;
import com.example.e_commerce.repository.OrderRepository;
import com.example.e_commerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final Random random = new Random();
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public Order createOrder(Order order){
        long trackingNumber;
        do {
            trackingNumber = 10000000L + random.nextInt(90000000); // 8-digit number
        } while (orderRepository.existsByTrackingNumber(trackingNumber));

        order.setTrackingNumber(trackingNumber);
        return orderRepository.save(order);
    }
    @Override
    public Order getOrderById(Long id){
        Order order = orderRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Order does not exist!"));
        return order;
    }
    @Override
    public List<Order> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders;
    }
    @Override
    public void deleteOrder(Long id){
        Order order = orderRepository
                .findById(id)
                        .orElseThrow(()->new RuntimeException("Order does not exist!"));
        orderRepository.deleteById(id);
    }
    @Override
    public Order updateOrder(Long id , Order updatedOrder){
        Order order = orderRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Order does not exist!"));
        if(!(updatedOrder.getTaxes() < 0) ){

        }

        if(updatedOrder.getTaxes() >=0){
            order.setTaxes(updatedOrder.getTaxes());
        }
        setIfNotNull(updatedOrder.getStatus(), order::setStatus);
        setIfNotNull(updatedOrder.getShippingAddress(), order::setShippingAddress);
        setIfNotNull(updatedOrder.getTrackingNumber(), order::setTrackingNumber);

        return orderRepository.save(order);
    }
    private <T> void setIfNotNull(T value, Consumer<T> setter) {

        if (value != null) {

            setter.accept(value);
        }

    }

}
