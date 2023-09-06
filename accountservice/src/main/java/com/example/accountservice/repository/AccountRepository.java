package com.example.accountservice.repository;

import com.example.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByUsername(String username);
    public Account findByPassword(String password);
}
