package com.example.e_commerce.service;

import com.example.e_commerce.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    void deleteProduct(Long id);

    Product updateProduct(Long id, Product updatedProduct);

    List<Product> getAllProducts();

    Product getProductById(Long id);
}
