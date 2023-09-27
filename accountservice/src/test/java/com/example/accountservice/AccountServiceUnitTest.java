package com.example.accountservice;

import com.example.accountservice.model.Account;
import com.example.accountservice.model.Role;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    public void givenAccount_whenSearchById_thenAccountIsReturned() {

        //given
        Integer accountId = 1;
        Account account = Account.builder()
                .idAccount(accountId)
                .name("Lollo")
                .surname("Verdi")
                .city("Roma")
                .address("Via Conte 123")
                .age(22)
                .username("drast")
                .email("lollo@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        given(accountRepository.findById(accountId)).willReturn(Optional.of(account));

        //when
        Optional<Account> result = accountService.getAccountById(accountId);

        //then
        assertTrue(result.isPresent());
        assertEquals(accountId, result.get().getIdAccount());
    }


    @Test
    public void givenAccount_whenSearchByName_thenAccountIsReturned() {

        //given
        Integer accountId = 1;
        Account account = Account.builder()
                .idAccount(accountId)
                .name("Lollo")
                .surname("Verdi")
                .city("Roma")
                .address("Via Conte 123")
                .age(22)
                .username("drast")
                .email("lollo@gmail.com")
                .password("1234")
                .role(Role.USER)
                .build();
        given(accountRepository.findByName(account.getName())).willReturn(account);

        //when
        Optional<Account> result = accountService.getAccountByName(account.getName());

        //then
        assertTrue(result.isPresent());
        assertEquals(account.getName(), result.get().getName());
    }


    @Test
    public void givenAccountDoesNotExist_whenDeleteAccountById_thenThrowException() {
        //given
        given(accountRepository.existsById(1)).willReturn(false);

        //when
        Throwable throwable = assertThrows(NoSuchElementException.class, () -> accountService.deleteAccountById(1));

        //then
        assertThat(throwable, is(notNullValue()));

    }





}
