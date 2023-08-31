package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.ProductRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepositroy productRepositroy;

    public List<Product> getAllProduct() {
        return productRepositroy.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepositroy.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepositroy.save(product);
    }

    public Product updateProduct(int id, Product p) {
        Product product = productRepositroy.getOne(id);

        //update product details
        product.setName(p.getName());
        product.setCodeProduct(p.getCodeProduct());

        return productRepositroy.save(product);

    }


    public void deleteProduct(int id) {
        productRepositroy.deleteById(id);
    }
}
