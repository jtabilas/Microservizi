package com.example.cartserver.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private int idCart;

    @Column(name = "quantity_product")
    private int quantityProduct;

    @Column(name = "total_price")
    private float totalPrice;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="cartID")
    private List<CartItem> cartItems = new ArrayList<>();

}
