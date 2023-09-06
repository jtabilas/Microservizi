package com.example.accountservice.service;

import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private AccountRepository repo;


    private Account acc;

    public List<Account> getAllAccount() {
        return repo.findAll();
    }

    public Account getAccountById(int id) {
        return repo.findById(id).orElseThrow();
    }

    public Account addAccount(Account account) {
        return repo.save(account);
    }

    public Account updateAccountById(int id, Account account) {
        Account updateAccount = repo.findById(id).orElseThrow();

        updateAccount.setName(account.getName());
        updateAccount.setSurname(account.getSurname());
        updateAccount.setCity(account.getCity());
        updateAccount.setAddress(account.getAddress());
        updateAccount.setAge(account.getAge());
        updateAccount.setUsername(account.getUsername());
        updateAccount.setEmail(account.getEmail());
        updateAccount.setPassword(account.getPassword());

        return  repo.save(updateAccount);
    }

    public String deleteAccountById(int id) {
        repo.deleteById(id);
        return "Account deleted";
    }

    //check if account is present on db
    public String checkAccount(Account account) {

        try {
            // find the user and password on db
            Account username = repo.findByUsername(account.getUsername());
            Account password = repo.findByPassword(account.getPassword());

            // this check if user and pass is null and launch the exception
            username.getUsername();
            password.getPassword();
        } catch(Exception e) {
            throw new AccountNotFoundException("Account doesn't exist");
        }

        return "Login succes";

    }
}
