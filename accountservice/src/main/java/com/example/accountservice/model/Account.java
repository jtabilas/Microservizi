package com.example.accountservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAccount;
    private String name;
    private String surname;
    private String city;
    private String address;
    private int age;
    private String username;
    private String email;
    private String password;

    public Account() {}

}
