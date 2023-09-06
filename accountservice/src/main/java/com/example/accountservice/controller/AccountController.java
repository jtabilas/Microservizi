package com.example.accountservice.controller;

import com.example.accountservice.model.Account;
import com.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/accounts")
    public List<Account> getAllAccount() {
        return service.getAllAccount();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable int id) {
        return service.getAccountById(id);
    }

    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody Account account) {
        return service.addAccount(account);
    }

    @PutMapping("/updateAccount/{id}")
    public Account updateAccount(@PathVariable int id, @RequestBody Account account) {
        return service.updateAccountById(id, account);
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable int id) {
        return service.deleteAccountById(id);
    }

    // function for login
    @GetMapping("/login")
    public String findAccount(@RequestBody Account account) {
        return service.checkAccount(account);
    }

}
