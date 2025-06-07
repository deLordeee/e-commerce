package com.example.e_commerce.entity;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "order_table")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('DELIVERED', 'PENDING', 'PROCESSING', 'SHIPPED')")
    private OrderStatus status;
    @Column(nullable = false)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('CARD', 'CASH')")
    private PaymentMethod method;
    @Column(unique = true)

    private Long trackingNumber;
    @Column(unique = true)
    private double taxes;

    @Column(unique = true)
    private double totalPrice;


}
