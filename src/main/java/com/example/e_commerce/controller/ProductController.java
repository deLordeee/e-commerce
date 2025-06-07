package com.example.e_commerce.controller;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.ProductCategory;
import com.example.e_commerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
         productService.addProduct(product);
         return new ResponseEntity<>(product , HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return HttpStatus.NO_CONTENT;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id , @RequestBody Product product){

        return new ResponseEntity<>(productService.updateProduct(id , product), HttpStatus.OK);
    }
}
