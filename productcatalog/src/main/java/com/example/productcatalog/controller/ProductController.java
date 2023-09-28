package com.example.productcatalog.controller;


import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {


    @Autowired
    private ProductService service;

    //get all product catalog
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(service.getAllProduct());
    }

    //get product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    //add product
    @PostMapping("/addProducts")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    //update product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return  ResponseEntity.ok(service.updateProduct(id, product));
    }


    //delete product by id
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        return new ResponseEntity<>(service.deleteProduct(id),HttpStatus.OK);
    }

}
