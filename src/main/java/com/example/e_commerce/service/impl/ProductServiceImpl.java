package com.example.e_commerce.service.impl;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id){
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product does not exists!"));
        productRepository.deleteById(id);

    }
    @Override
    public Product updateProduct(Long id , Product updatedProduct){
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product does not exist!"));
        setIfNotNull(updatedProduct.getName(), product::setName);
        setIfNotNull(updatedProduct.getPrice(), product::setPrice);
        setIfNotNull(updatedProduct.getDescription(), product::setDescription);
        setIfNotNull(updatedProduct.getRating(), product::setRating);
        setIfNotNull(updatedProduct.getQuantity(), product::setQuantity);
        setIfNotNull(updatedProduct.getReviews(), product::setReviews);
        setIfNotNull(updatedProduct.getCategoryId(), product::setCategoryId);
        setIfNotNull(updatedProduct.getBrand(), product::setBrand);
        return productRepository.save(product);
    }
    private <T> void setIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
    @Override
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }
    @Override
    public Product getProductById(Long id){
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product does not exist!"));
        return product;
    }

}
