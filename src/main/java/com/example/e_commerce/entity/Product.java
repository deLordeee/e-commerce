package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private double price;
    private Long categoryId;
    @Enumerated(EnumType.STRING)
    private ProductBrand brand;
    private int quantity;
    private double rating;
    private int reviews;
    private boolean availability;
}
