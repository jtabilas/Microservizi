package com.example.accountservice.service;

import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.exception.EmailExistsException;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private AccountRepository repo;


    public List<Account> getAllAccount() {
        return repo.findAll();
    }

    public Optional<Account> getAccountById(int id) {
        return repo.findById(id);
    }

    public Optional<Account> getAccountByName(String name) {
        return Optional.ofNullable(repo.findByName(name));
    }

    public Account updateAccountById(int id, Account account) {
        Account updateAccount = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        updateAccount.setName(account.getName());
        updateAccount.setSurname(account.getSurname());
        updateAccount.setCity(account.getCity());
        updateAccount.setAddress(account.getAddress());
        updateAccount.setAge(account.getAge());
        updateAccount.setUsername(account.getUsername());
        updateAccount.setEmail(account.getEmail());
        updateAccount.setPassword(account.getPassword());
        updateAccount.setRole(account.getRole());

        return  repo.save(updateAccount);
    }

    public void deleteAccountById(int id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Account with ID " + id + " not found");
        }
        repo.deleteById(id);
    }



}
