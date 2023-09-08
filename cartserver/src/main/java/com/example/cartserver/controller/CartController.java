package com.example.cartserver.controller;

import com.example.cartserver.model.Cart;
import com.example.cartserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {


    @Autowired
    private CartService service;

    // get all cart
    @GetMapping("/cart")
    public List<Cart> getAllCart() {
        return service.getAllCart();
    }

    // get cart by id
    @GetMapping("/cart/{id}")
    public Cart getCartById(@PathVariable int id) {
        return service.getCartById(id);
    }

    // add cart
    @PostMapping("/cart")
    public Cart addCart(@RequestBody Cart cart) {
        return service.addCart(cart);
    }


    // delete cart
    @DeleteMapping("/cart/{id}")
    public String deleteCart(@PathVariable int id) {
        return service.deleteCart(id);
    }

    //update cart
    @PutMapping("/cart/{id}")
    public Cart updateCart(@PathVariable int id, @RequestBody Cart cart) {
        return service.updateCart(id, cart);
    }
}
