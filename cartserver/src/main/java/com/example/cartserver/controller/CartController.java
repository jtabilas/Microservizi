package com.example.cartserver.controller;


import com.example.cartserver.model.Cart;
import com.example.cartserver.model.CartItem;
import com.example.cartserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CartController {


    @Autowired
    private CartService service;

    // Creazione del carello
    @PostMapping("/cart")
    public ResponseEntity<Cart> createCart(){
        return new ResponseEntity<>(service.createCart(), HttpStatus.CREATED);
    }

    // Aggiunganta di item nel carello
    @PostMapping("/addCart/{cartId}/{productId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable int cartId,@PathVariable int productId, @PathVariable int quantity) {
        return new ResponseEntity<>(service.addToCart(productId, quantity, cartId),HttpStatus.OK);
    }


    // Lista degli items nel carello
    @GetMapping("/cart")
    public ResponseEntity<?> getCart() {
        return new ResponseEntity<>(service.getCart(), HttpStatus.OK);
    }

    // Elimina un item nel carello
    @DeleteMapping("/deleteItem/{idCart}/{idItemCart}")
    public ResponseEntity<?> deleteItemCart(@PathVariable int idCart,@PathVariable int idItemCart) {
        return  new ResponseEntity<>(service.deleteCartItem(idCart,idItemCart), HttpStatus.OK);
    }

    // Elimina il carello
    @DeleteMapping("/deleteAll/{idCart}")
    public ResponseEntity<?> deleteAlL(@PathVariable int idCart) {
        return new ResponseEntity<>(service.deleteCart(idCart), HttpStatus.OK);
    }


    // Aggiorna
    @PutMapping("/updateItem/{idCart}/{idItemCart}")
    public ResponseEntity<?> updateItem(@PathVariable int idCart,@PathVariable int idItemCart, @RequestBody CartItem cartItem) {
        return  new ResponseEntity<>(service.updateCartItem(idCart,idItemCart ,cartItem), HttpStatus.OK);
    }

}
