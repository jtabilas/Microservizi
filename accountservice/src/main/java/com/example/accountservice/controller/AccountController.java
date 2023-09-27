package com.example.accountservice.controller;

import com.example.accountservice.model.Account;
import com.example.accountservice.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService service;


    // Get all accounts
    @GetMapping("/accounts")
    public List<Account> getAllAccount() {
        return service.getAllAccount();
    }


    // Get account by id
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        return service.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Get account by name
    @GetMapping("/accounts/name/{name}")
    public ResponseEntity<Account> getAccountByName(@PathVariable String name) {
        return service.getAccountByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update account
    @PutMapping("/updateAccount/{id}")
    public Account updateAccount(@PathVariable int id, @RequestBody Account account) {
        return service.updateAccountById(id, account);
    }

    // Delete account
    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") int id) {
         service.deleteAccountById(id);
        return new ResponseEntity<>("Account deleted successfully!.", HttpStatus.OK);
    }


}
