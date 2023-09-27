package com.example.accountservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingUpRequest {
    private String name;
    private String surname;
    private String city;
    private String address;
    private int age;
    private String username;
    private String email;
    private String password;
}
