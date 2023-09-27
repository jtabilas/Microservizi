package com.example.accountservice;

import com.example.accountservice.model.Account;
import com.example.accountservice.model.Role;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AccountserviceApplication  implements CommandLineRunner {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AccountserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //accountRepository.save(new Account(1,"franco","verdi","Roma","Via Nazionale 234",24,"drast89","franco@gmail.com",passwordEncoder.encode("1234"), Role.ADMIN));
    }

}
