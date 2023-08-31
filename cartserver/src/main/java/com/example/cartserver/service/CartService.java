package com.example.cartserver.service;

import com.example.cartserver.model.Cart;
import com.example.cartserver.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private  CartRepository cr;

    public List<Cart> getAllCart() {
        return cr.findAll();
    }

    public Optional<Cart> getCartById(int id) {
        return cr.findById(id);
    }

    public Cart addCart(Cart cart) {
        return  cr.save(cart);
    }

    public void deleteCart(int id) {
        cr.deleteById(id);
    }

    public Cart updateCart(int id, Cart cart) {
        Cart cart1 = cr.getOne(id);

        cart1.setQuantity(cart.getQuantity());
        cart1.setStatus(cart.getStatus());

        return cr.save(cart1);
    }

}
