package com.example.cartserver.service;

import com.example.cartserver.model.Cart;
import com.example.cartserver.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CartService {

    @Autowired
    private  CartRepository repo;

    public List<Cart> getAllCart() {
        return repo.findAll();
    }

    public Cart getCartById(int id) {
        return repo.findById(id).get();
    }

    public Cart addCart(Cart cart) {
        return  repo.save(cart);
    }

    public String deleteCart(int id) {
        Cart cartDelete = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(cartDelete != null) {
            repo.deleteById(id);
        }
        return "Cart delete success";
    }

    public Cart updateCart(int id, Cart cart) {
        Cart updateCart = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updateCart.setQuantityProduct(cart.getQuantityProduct());
        updateCart.setShippingPrice(cart.getShippingPrice());

        return repo.save(updateCart);
    }

}
