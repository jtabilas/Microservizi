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
    private CartService cs;

    // get all cart
    @GetMapping("/cart")
    public List<Cart> getAllCart() {
        return cs.getAllCart();
    }

    // get cart by id
    @GetMapping("/cart/{id}")
    public Optional<Cart> getCartById(@PathVariable int id) {
        return cs.getCartById(id);
    }

    // add cart
    @PostMapping("/cart")
    public Cart addCart(@RequestBody Cart cart) {
        return cs.addCart(cart);
    }


    // delete cart
    @DeleteMapping("/cart/{id}")
    public void deleteCart(@PathVariable int id) {
        cs.deleteCart(id);
    }

    //update cart
    @PutMapping("/cart/{id}")
    public Cart updateCart(@PathVariable int id, @RequestBody Cart cart) {
        return cs.updateCart(id, cart);
    }
}
