package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        Product tempProduct = productRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
        return tempProduct;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product p) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));

        //update product details
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setCategory(p.getCategory());
        product.setPrice(p.getPrice());
        product.setCodeProduct(p.getCodeProduct());
        product.setAviability(p.getAviability());

        return productRepository.save(product);

    }


    public String deleteProduct(int id) {
        productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productRepository.deleteById(id);
        return "Product delete success";
    }
}
