package com.example.cartserver.service;

import com.example.cartserver.dto.ProductDto;
import com.example.cartserver.model.Cart;
import com.example.cartserver.model.CartItem;
import com.example.cartserver.repository.CartItemRepository;
import com.example.cartserver.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;



@Service
public class CartService {

    @Autowired
    private CartRepository repo;

    @Autowired
    private CartItemRepository itemRepo;

    @Autowired
    private WebClient webClient;

    public Cart createCart() {
        Cart cart = new Cart();
        cart.setQuantityProduct(0);
        return repo.save(cart);
    }

    public String addToCart(int productID, int quantity, int cartID) {
        ProductDto productDto = webClient.get()
                .uri("http://localhost:8082/api/products/" + productID)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();

        // Verifica la disponibilità del prodotto
        if (productDto.getAvailability() < quantity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantità non disponibile");
        }

        Cart cart = repo.findById(cartID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrello non trovato"));

        // Imposta productDto
        productDto.setId(productID);
        productDto.setAvailability(productDto.getAvailability() - quantity); // Aggiorna la disponibilità

        // Crea un nuovo oggetto CartItem con le informazioni del prodotto
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productDto.getId());
        cartItem.setName(productDto.getName());
        cartItem.setDescription(productDto.getDescription());
        cartItem.setCategory(productDto.getCategory());
        cartItem.setCodeProduct(productDto.getCodeProduct());
        cartItem.setPrice(productDto.getPrice());
        cartItem.setAvailability(productDto.getAvailability());
        cartItem.setQuantity(quantity);



        // Aggiungi il nuovo oggetto CartItem al carrello
        cart.getCartItems().add(cartItem);

        // Aggiorna il prodotto
        updateProduct(productDto);

        // Aggiorna il totale delle quantità nel carrello
        cart.setQuantityProduct(cart.getQuantityProduct() + quantity);

        // Aggiorna il prezzo totale del carello
        cart.setTotalPrice(cart.getTotalPrice() + (cartItem.getPrice() * cartItem.getQuantity()));

        repo.save(cart);
        return (cartItem.getName() +" aggiunto correttamente nel carello!");
    }

    // PUT per aggiornare la disponibilità del prodotto
    public void updateProduct(ProductDto productDto) {
        webClient.put()
                .uri("http://localhost:8082/api/products/" + productDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(productDto))
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }

    // GET Lista del carello
    public List<Cart> getCart() {
        return repo.findAll();
    }

    // Elimina un item nel carello
    public String deleteCartItem(int idCart, int idCartItem) {
        repo.findById(idCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CartItem cartItem = itemRepo.findById(idCartItem).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCart(idCart, cartItem);

        // PUT
        ProductDto productDto = new ProductDto();
        productDto.setId(cartItem.getProductId());
        productDto.setName(cartItem.getName());
        productDto.setDescription(cartItem.getDescription());
        productDto.setCategory(cartItem.getCategory());
        productDto.setCodeProduct(cartItem.getCodeProduct());
        productDto.setPrice(cartItem.getPrice());
        productDto.setAvailability(cartItem.getAvailability() + cartItem.getQuantity());

        // aggiorna il product service
        updateProduct(productDto);

        itemRepo.deleteById(idCartItem);
        return   (cartItem.getName() + " eliminato nel carello!");
    }

    // PUT Aggiorna la quantità di un item
    public CartItem updateCartItem(int idCart,int idItemCart,CartItem cartItem) {
        Cart cart = repo.findById(idCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CartItem updateItem = itemRepo.findById(idItemCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int temp = updateItem.getQuantity();

        updateItem.setCartID(updateItem.getCartID());
        updateItem.setQuantity(cartItem.getQuantity()); // viene aggiornato
        updateItem.setName(updateItem.getName());
        updateItem.setDescription(updateItem.getDescription());
        updateItem.setCategory(updateItem.getCategory());
        updateItem.setPrice(updateItem.getPrice());
        updateItem.setCategory(updateItem.getCategory());
        updateItem.setCodeProduct(updateItem.getCodeProduct());
        updateItem.setAvailability(updateItem.getAvailability());

        int qtyUpdate = updateItem.getQuantity();

        // Aggiorna il carello
        while(temp > qtyUpdate) {
            updateCart(cart.getIdCart(), updateItem);
            temp = temp - qtyUpdate;
        }


        itemRepo.save(updateItem);
        return  updateItem;
    }

    // Aggiorna il carello quando elimino un item
    public void updateCart(int idCart, CartItem cartItem) {
        Cart cart = repo.findById(idCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        cart.setQuantityProduct(cart.getQuantityProduct() - cartItem.getQuantity());
        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getQuantity() * cartItem.getPrice()));
        repo.save(cart);
    }


    // Elimina tutto il carello
    public String deleteCart(int idCart) {
        repo.findById(idCart).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repo.deleteById(idCart);
        return "Carello vuoto!";
    }

}

