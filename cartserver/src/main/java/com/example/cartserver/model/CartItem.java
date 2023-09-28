package com.example.cartserver.model;

import com.example.cartserver.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartItem")
    private int id;

    private Integer cartID;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_id")
    private int productId;
    private String name;
    private String description;
    private String category;
    private String codeProduct;
    private float   price;
    private int availability;
}
