package com.example.accountservice.security.service;


import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.HttpStatusCodeException;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws HttpStatusCodeException {
        try {
            return repo.findByUsername(username)
                    .orElseThrow(() -> new HttpStatusCodeException(HttpStatusCode.valueOf(401),"Unauthorized") {
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
