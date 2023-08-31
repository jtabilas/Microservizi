package com.example.productcatalog.controller;


import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {


    @Autowired
    private ProductService ps;

    //get all product catalog
    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return ps.getAllProduct();
    }

    //get product by id
    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return ps.getProductById(id);
    }

    //add product
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return ps.addProduct(product);
    }

    //update product
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return  ps.updateProduct(id, product);
    }


    //delete product by id
    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable int id) {
        ps.deleteProduct(id);
    }

}
